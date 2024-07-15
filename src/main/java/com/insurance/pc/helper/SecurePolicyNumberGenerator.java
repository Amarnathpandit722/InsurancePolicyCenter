package com.insurance.pc.helper;

import java.security.SecureRandom;

public class SecurePolicyNumberGenerator {

	
	 private static final SecureRandom secureRandom = new SecureRandom();
	    private static final int POLICY_NUMBER_LENGTH = 10;

		public static String generatePolicyNumber() {
	        StringBuilder policyNumber = new StringBuilder(POLICY_NUMBER_LENGTH);

	        // Generate the first digit (1-9) to ensure it's not zero
	        int firstDigit = secureRandom.nextInt(9) + 1;
	        policyNumber.append(firstDigit);

	        // Generate the remaining digits (0-9)
	        for (int i = 1; i < POLICY_NUMBER_LENGTH; i++) {
	            int digit = secureRandom.nextInt(10);
	            policyNumber.append(digit);
	        }

	        return policyNumber.toString();
	    }
}
