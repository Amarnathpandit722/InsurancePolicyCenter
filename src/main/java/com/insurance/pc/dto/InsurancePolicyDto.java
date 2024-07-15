package com.insurance.pc.dto;

import java.util.Set;

public record InsurancePolicyDto(
		String id,
		String policyNumber,
		String policyType,
		double premiumCoverageAmount,
		double policyPremiumAmount,
		String policyPremium,
		String policyStartDate,
		String policyEndDate,
		String policyStatus,
		double brokerCompensataion,
		String nationalId,
		String clientId,
		String bokerId,
//		String coverage,
//		String coverable
		Set<String> coverages,
		Set<String> coverables
		) {

}
