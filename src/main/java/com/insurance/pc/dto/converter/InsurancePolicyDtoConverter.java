package com.insurance.pc.dto.converter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.request.insurance.CreateRequestInsurance;
import com.insurance.pc.helper.CalculateBrokerageCompensation;
import com.insurance.pc.helper.CalculatePremiumAmount;
import com.insurance.pc.helper.PolicyPremium;
import com.insurance.pc.helper.PolicyType;
import com.insurance.pc.helper.Status;
import com.insurance.pc.model.Coverables;
import com.insurance.pc.model.Coverage;
import com.insurance.pc.model.InsurancePolicy;
import com.insurance.pc.repository.CoverablesRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InsurancePolicyDtoConverter {

	@Autowired
	private static CoverablesRepository coverablesRepository;

	public InsurancePolicyDto convert(InsurancePolicy from) {
		return new InsurancePolicyDto(from.getId(), from.getPolicyNumber(),
				from.getInsuracePolicyType() != null ? from.getInsuracePolicyType().name() : null,
				from.getInsurancePolicyCoverageAmount(), from.getInsurancePolicyPremiumAmount(),
				from.getInsurancePolicyPremium() != null ? from.getInsurancePolicyPremium().name() : null,
				from.getInsurancePolicyStartDate(), from.getInsurancePolicyEndDate(),
				from.getPolicyStatus() != null ? from.getPolicyStatus().name() : null, from.getBrokerCompensation(),
				from.getNationalId(), from.getClient().getId(), from.getBroker().getId(),
				mapCoverage(from.getCoverages()), mapCoverable(from.getCoverables()));

	}

	private Set<String> mapCoverable(Set<Coverables> coverables) {
		if (coverables == null) {
			return null;
		}
		return coverables.stream().map(Coverables::getCoverablesType).collect(Collectors.toSet());

	}

	private Set<String> mapCoverage(Set<Coverage> coverages) {
		if (coverages == null) {
			return null;
		}
		return coverages.stream().map(Coverage::getCoverageType).collect(Collectors.toSet());

	}

	public List<InsurancePolicyDto> convert(List<InsurancePolicyDto> from) {
		return from.stream().collect(Collectors.toList());
	}

	public List<InsurancePolicyDto> convertList(List<InsurancePolicy> from) {
		return from.stream().map(this::convert).collect(Collectors.toList());

	}

	@Transactional(rollbackFor = Exception.class)
	public static void setInsuranceProperties(InsurancePolicy insurance, CreateRequestInsurance request) {
		PolicyType policyType = PolicyType.valueOf(request.getInsurancePolicyType().toUpperCase());
		PolicyPremium policyPremium = PolicyPremium.valueOf(request.getPolicyPremium().toUpperCase());
		Status policyStatus = Status.valueOf(request.getPolicyStatus().toUpperCase());

		String tenure = request.getPolicyYears();
		Long convertedTenure = Long.valueOf(tenure) * 12;
		String convertedStringTenure = String.valueOf(convertedTenure);

		double insurancePremiumAmount = CalculatePremiumAmount.calculatePremium(policyPremium,
				request.getInsurancePolicyCoverageAmount(), tenure);
		log.info("insurancePremiumAmount : {}", insurancePremiumAmount);

		double brokerage = CalculateBrokerageCompensation.calculateBrokerage(policyType,
				request.getInsurancePolicyCoverageAmount(), insurancePremiumAmount);
		log.info("brokerage AMount : {}", brokerage);

		double brokerageCompoensation = Math.abs(brokerage - insurancePremiumAmount);
		log.info("Broker Compensation AMount : {}", brokerageCompoensation);

		List<BiConsumer<InsurancePolicy, CreateRequestInsurance>> setters = List.of(
				(i, r) -> i.setInsuracePolicyType(policyType),
				(i, r) -> i.setInsurancePolicyCoverageAmount(r.getInsurancePolicyCoverageAmount()),
				(i, r) -> i.setInsurancePolicyPremium(policyPremium),
				(i, r) -> i.setInsurancePolicyStartDate(r.getInsurancePolicyStartDate()),
				(i, r) -> i.setInsurancePolicyEndDate(r.getInsurancePolicyEndDate()),
				(i, r) -> i.setPolicyStatus(policyStatus), (i, r) -> i.setNationalId(r.getNationalId()),
				(i, r) -> i.setPremiumTenure(convertedStringTenure),
				(i, r) -> i.setInsurancePolicyPremiumAmount(insurancePremiumAmount),
				(i, r) -> i.setBrokerCompensation(brokerageCompoensation),

				(i, r) -> {
					Coverables coverables = new Coverables();
					String id = r.getPolicyCoverableId();
					Set<Coverables> coverablesSet = new HashSet<>();

					coverables = coverablesRepository.findById(id).orElse(null);
					coverables.setCoverablesType(coverables.getCoverablesType());
					coverables.setDescription(coverables.getDescription());
					coverablesSet.add(coverables);
					i.setCoverables(coverablesSet);

					i.getCoverables().add(coverables);
				}, (i, r) -> {
					Coverage coverage = new Coverage();
					coverage.setCoverageType(r.getPolicyCoverageId());
					i.getCoverages().add(coverage);

				}

		);

		setters.forEach(setter -> setter.accept(insurance, request));

	}

}
