package com.insurance.pc.helper;

public class CalculateBrokerageCompensation {

	public static double calculateBrokerage(PolicyType policyType, double insurancePolicyCoverageAmount,double policyPremium) {
		 double brokerage = 0.0;

		    switch (policyType) {
		        case HEALTHCARE_INSURANCE:
		            brokerage = insurancePolicyCoverageAmount * 0.005;
		            policyPremium+=brokerage;
		            
		            break;
		        case PERSONAL_AUTO:
		            // Calculate brokerage for personal auto insurance (example calculation)
		            // Assuming 1% of coverage amount for personal auto
		            brokerage = insurancePolicyCoverageAmount * 0.001;
		            policyPremium+=brokerage;
		            break;
		        case PROPERTY_INSURANCE:
		            // Calculate brokerage for property insurance (example calculation)
		            // Assuming 2% of premium amount for property insurance
		            brokerage = policyPremium * 0.002;
		            policyPremium+=brokerage;

		            break;
		        case GENRAL_INSURANCE:
		            // Calculate brokerage for general insurance (example calculation)
		            // Assuming 1.5% of coverage amount for general insurance
		            brokerage = insurancePolicyCoverageAmount * 0.005;
		            policyPremium+=brokerage;

		            break;
		        case WORKER_INSURANCE:
		            // Calculate brokerage for worker insurance (example calculation)
		            // Assuming 0.8% of premium amount for worker insurance
		            brokerage = policyPremium * 0.008;
		            policyPremium+=brokerage;

		            break;
		        case INLAND_MARINE:
		            // Calculate brokerage for inland marine insurance (example calculation)
		            // Assuming 1.2% of coverage amount for inland marine insurance
		            brokerage = insurancePolicyCoverageAmount * 0.002;
		            policyPremium+=brokerage;

		            break;
		        default:
		            throw new IllegalArgumentException("Unsupported policy type: " + policyType);
		    }

		    return policyPremium;
	}

}
