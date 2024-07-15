package com.insurance.pc.dto.converter;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.pc.dto.AddressDto;
import com.insurance.pc.dto.ClientDto;
import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.request.client.CreateClientRequest;
import com.insurance.pc.helper.SecurePolicyNumberGenerator;
import com.insurance.pc.model.Address;
import com.insurance.pc.model.Broker;
import com.insurance.pc.model.Client;
import com.insurance.pc.model.Coverables;
import com.insurance.pc.model.Coverage;
import com.insurance.pc.model.InsurancePolicy;

@Component
public class ClientDtoConverter {


	public ClientDto convert(Client from) {
		return new ClientDto(from.getId(), from.getFirstName(), from.getLastName(), from.getEmail(),
				from.getPhoneNumber(), from.getDateOfBirth(),

				mapPolicies(from.getPolicies()), mapAddresses(from.getAddressList(), from.getId()));
	}

	public List<ClientDto> convert(List<Client> from) {
		return from.stream().map(this::convert).collect(Collectors.toList());
	}

	private Set<InsurancePolicyDto> mapPolicies(Set<InsurancePolicy> policies) {
		if (policies == null) {
			return null;
		}
		return policies.stream().map(policy -> new InsurancePolicyDto(

				policy.getId(), policy.getPolicyNumber(),
				Optional.ofNullable(policy.getInsuracePolicyType()).map(Enum::name).orElse(null),
				policy.getInsurancePolicyCoverageAmount(), policy.getInsurancePolicyPremiumAmount(),
				Optional.ofNullable(policy.getInsurancePolicyPremium()).map(Enum::name).orElse(null),
				policy.getInsurancePolicyStartDate(), policy.getInsurancePolicyEndDate(),
				Optional.ofNullable(policy.getPolicyStatus()).map(Enum::name).orElse(null),
				policy.getBrokerCompensation(), policy.getNationalId(),
				Optional.ofNullable(policy.getClient()).map(Client::getId).orElse(null),
				Optional.ofNullable(policy.getBroker()).map(Broker::getId).orElse(null),
				mapCoverage(policy.getCoverages()), mapCoverable(policy.getCoverables()))).collect(Collectors.toSet());
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

	private List<AddressDto> mapAddresses(List<Address> addresses, String clientId) {
		if (addresses == null) {
			return null;
		}
		return addresses.stream()
				.map(address -> new AddressDto(address.getId(), address.getStreet(), address.getCity(),
						address.getState(), address.getZipCode(), address.getAddressType().name(), clientId))
				.collect(Collectors.toList());
	}

    @Transactional(rollbackFor = Exception.class)
	public static void setClientDtoInformation(Client client, CreateClientRequest request) {
		List<BiConsumer<Client, CreateClientRequest>> setters = List.of((c, r) -> c.setFirstName(r.getFirstName()),
				(c, r) -> c.setLastName(r.getLastName()), (c, r) -> c.setEmail(r.getEmail()),
				(c, r) -> c.setPhoneNumber(r.getPhoneNumber()), (c, r) -> c.setDateOfBirth(r.getDateOfBirth()));

		if (request.getAddressDto() != null) {
			List<Address> addresses = request.getAddressDto().stream().map(addrReq -> {
				Address address = new Address();
				AddressDtoConverter.setAddressProperties(address, addrReq);

				return address;
			}).collect(Collectors.toList());
			client.setAddressList(addresses);
		}

		if (request.getInsurancePolicyRequest() != null) {
			Set<InsurancePolicy> policies = request.getInsurancePolicyRequest().stream().map(policyReq -> {
				InsurancePolicy policy = new InsurancePolicy();
				String policyNumber = SecurePolicyNumberGenerator.generatePolicyNumber();
				policy.setPolicyNumber(policyNumber);

				InsurancePolicyDtoConverter.setInsuranceProperties(policy, policyReq);

				return policy;
			}).collect(Collectors.toSet());
			client.setPolicies(policies);
		}
		setters.forEach(setter -> setter.accept(client, request));

	}

}
