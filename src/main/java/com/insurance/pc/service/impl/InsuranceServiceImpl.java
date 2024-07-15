package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.converter.InsurancePolicyDtoConverter;
import com.insurance.pc.dto.request.insurance.CreateRequestInsurance;
import com.insurance.pc.dto.request.insurance.UpdateRequestInsurance;
import com.insurance.pc.exception.ClientInformationNotAvailable;
import com.insurance.pc.exception.PolicyDetailsNotFoundException;
import com.insurance.pc.helper.Status;
import com.insurance.pc.model.Client;
import com.insurance.pc.model.InsurancePolicy;
import com.insurance.pc.repository.ClientRepository;
import com.insurance.pc.repository.InsurancePolicyRepository;
import com.insurance.pc.service.InsurancePolicyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceServiceImpl implements InsurancePolicyService {

	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InsurancePolicyDtoConverter insurancePolicyDtoConverter;

	@Override
	@Transactional(rollbackFor = { UnableToAddUpdateInsurancePolicyException.class, Exception.class })
	public InsurancePolicyDto createNewPolicy(CreateRequestInsurance request) {

		if (request == null) {
			throw new UnableToAddUpdateInsurancePolicyException("Unable to Save Address");
		}
		InsurancePolicy insurancePolicy = new InsurancePolicy();
		InsurancePolicyDtoConverter.setInsuranceProperties(insurancePolicy, request);
		insurancePolicyRepository.save(insurancePolicy);

		return insurancePolicyDtoConverter.convert(insurancePolicy);

	}

	@Override
	public InsurancePolicyDto updateCreatedPolicy(String id, UpdateRequestInsurance request) {
		Optional<InsurancePolicy> optionalInsurance = insurancePolicyRepository.findById(id);
		if (optionalInsurance.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy does not Exists with Id:  " + id);
		}

		InsurancePolicy insurance = optionalInsurance.get();
//			InsurancePolicyDtoConverter.setInsuranceProperties(insurance, request);

		insurancePolicyDtoConverter.convert(insurance);

		return null;
	}

	@Override
	public InsurancePolicyDto getPolicyDetailsById(String id) {
		Optional<InsurancePolicy> listOfPolicy = insurancePolicyRepository.findById(id);

		if (listOfPolicy.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy with This Id does not Exists: " + id);

		}

		return insurancePolicyDtoConverter.convert(listOfPolicy.get());

	}

	@Override
	public List<InsurancePolicyDto> getAllPolicyDto() {
		List<InsurancePolicy> listOfPolicy = insurancePolicyRepository.findAll();

		if (listOfPolicy.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy does not Exists: ");

		}

		return insurancePolicyDtoConverter.convertList(listOfPolicy);
	}

	@Override
	public InsurancePolicyDto getPolicyByPolicyNumber(String policyNumber) {
		Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findByPolicyNumber(policyNumber);
		if (optionalPolicy.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy with This Policy Number does not Exists: " + policyNumber);
		}
		InsurancePolicy insurance = optionalPolicy.get();

		return insurancePolicyDtoConverter.convert(insurance);
	}

	@Override
	public List<InsurancePolicyDto> getPolicyDetailsByNationalId(String nationalId) {
		List<InsurancePolicy> policyList = insurancePolicyRepository.findByNationalId(nationalId);

		if (policyList.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy with This National Id does not Exists: " + nationalId);

		}

		List<InsurancePolicyDto> listOfPolicy = insurancePolicyDtoConverter.convertList(policyList);

		return listOfPolicy;
	}

	@Override
	public List<InsurancePolicyDto> getPolicyDetialsByBrokerId(String brokerId) {
		List<InsurancePolicy> policyList = insurancePolicyRepository.findByBrokerId(brokerId);
		if (policyList.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy with This Broker Id does not Exists: " + brokerId);

		}
		List<InsurancePolicyDto> listOfPolicy = insurancePolicyDtoConverter.convertList(policyList);

		return listOfPolicy;
	}

	@Override
	public InsurancePolicyDto updateClientId(String clientId, String policyId) {
		Client client = clientRepository.findById(clientId).orElse(null);
		InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId).orElse(null);

		if (client == null) {
			throw new ClientInformationNotAvailable("Client Does not exists with Id : " + clientId);
		}
		if (insurancePolicy == null) {
			throw new InsurancePolicyNotAvailable("Insurance is Not Available with this id " + policyId);
		}

		insurancePolicy.setClient(client);

		return insurancePolicyDtoConverter.convert(insurancePolicy);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override

	public InsurancePolicyDto cancelPolicy(String id) {
		Optional<InsurancePolicy> optionalInsurance = insurancePolicyRepository.findById(id);
		if (optionalInsurance.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy does not exist with Id: " + id);
		}

		InsurancePolicy insurance = optionalInsurance.get();
		insurance.setPolicyStatus(Status.CANCELLED);
		insurancePolicyRepository.save(insurance);
		return insurancePolicyDtoConverter.convert(insurance);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public InsurancePolicyDto renewPolicy(String id, String newEndDate) {
		Optional<InsurancePolicy> optionalInsurance = insurancePolicyRepository.findById(id);
		if (optionalInsurance.isEmpty()) {
			throw new PolicyDetailsNotFoundException("Policy does not exist with Id: " + id);
		}

		InsurancePolicy insurance = optionalInsurance.get();
		insurance.setInsurancePolicyEndDate(newEndDate);
		insurance.setPolicyStatus(Status.ACTIVE);
		insurancePolicyRepository.save(insurance);
		return insurancePolicyDtoConverter.convert(insurance);
	}
}
