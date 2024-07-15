package com.insurance.pc.dto.request.insurance;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class CreateRequestInsurance  extends BaseInsuranceRequest{

	
    @NotEmpty(message = "{ValidationMessage.Address.ADDRESS_CLIENT_ID_NOT_EMPTY}")
    @NotNull(message = "{ValidationMessage.Address.ADDRESS_CLIENT_ID_NOT_NULL}")
	private Long clientId;
	
	
	
	
	
	
}
