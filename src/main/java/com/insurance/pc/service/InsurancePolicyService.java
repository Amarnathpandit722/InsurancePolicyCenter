package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.request.insurance.CreateRequestInsurance;
import com.insurance.pc.dto.request.insurance.UpdateRequestInsurance;

public interface InsurancePolicyService {

	public InsurancePolicyDto createNewPolicy(CreateRequestInsurance request);

	public InsurancePolicyDto updateCreatedPolicy(String id, UpdateRequestInsurance request);

	public InsurancePolicyDto getPolicyDetailsById(String id);

	public List<InsurancePolicyDto> getAllPolicyDto();

	public InsurancePolicyDto getPolicyByPolicyNumber(String policyNumber);

	public List<InsurancePolicyDto> getPolicyDetailsByNationalId(String nationalId);

	public List<InsurancePolicyDto> getPolicyDetialsByBrokerId(String brokerId);
	
	public InsurancePolicyDto updateClientId(String clientId, String policyId);

	public InsurancePolicyDto cancelPolicy(String id);
	
	public InsurancePolicyDto renewPolicy(String id, String newEndDate);
}
