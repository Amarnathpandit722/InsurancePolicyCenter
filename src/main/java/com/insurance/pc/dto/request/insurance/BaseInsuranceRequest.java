package com.insurance.pc.dto.request.insurance;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseInsuranceRequest {

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_TYPE_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_TYPE_NOT_NULL}")
	private String insurancePolicyType;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_COVERAGE_AMOUNT_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_COVERAGE_AMOUNT_NOT_NULL}")
	private double insurancePolicyCoverageAmount;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_PREMIUM_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_PREMIUM_NOT_NULL}")
	private String policyPremium;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_START_DATE_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_START_DATE_NOT_NULL}")
	private String insurancePolicyStartDate;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_END_DATE_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.INSURANCE_POLICY_END_DATE_NOT_NULL}")
	private String insurancePolicyEndDate;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_STATUS_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_STATUS_NOT_NULL}")
	private String policyStatus;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.NATIONAL_ID_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.NATIONAL_ID_NOT_NULL}")
	private String nationalId;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.CLIENT_ID_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.CLIENT_ID_NOT_NULL}")
	private String clientId;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.BROKER_ID_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.BROKER_ID_NOT_NULL}")
	private String brokerId;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_YEARS_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_YEARS_NOT_NULL}")
	private String policyYears;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_COVERAGES_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_COVERAGES_NOT_NULL}")
	private String policyCoverageId;

	@NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_COVERABLES_NOT_EMPTY}")
	@NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_COVERABLES_NOT_NULL}")
	private String policyCoverableId;

//	    @NotEmpty(message = "{ValidationMessages.InsurancePolicy.POLICY_CALCULATED_PREMIUM_NOT_EMPTY}")
//	    @NotNull(message = "{ValidationMessages.InsurancePolicy.POLICY_CALCULATED_PREMIUM_NOT_NULL}")
//	    private List<CalculatedPremiumDto> policyCalculatedPremium;

}
