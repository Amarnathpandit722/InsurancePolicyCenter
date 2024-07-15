package com.insurance.pc.helper;

public class CalculatePremiumAmount {

	public static double calculatePremium(PolicyPremium policyPremium, double insurancePolicyCoverageAmount,
			String tenure) {
		double premium = 0.0;
		double yeartenure = Double.parseDouble(tenure);
		double yealryAmount = insurancePolicyCoverageAmount / yeartenure;
		switch (policyPremium) {
		case QUATERLY:
			premium = yealryAmount / 4;
			break;
		case MONTHLY:
			premium = yealryAmount / 12;
			break;
		case HALF_YEARLY:
			premium = yealryAmount / 6;
			break;
		case YEARLY:
			premium = yealryAmount;
			break;
		default:
			throw new IllegalArgumentException("Unsupported policy premium type: " + policyPremium);
		}

		return premium;
	}

}
