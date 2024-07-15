package com.insurance.pc.dto.request.address;

import com.insurance.pc.helper.ValidationMessage;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateAddressRequest extends BaseAddressRequest {

	
	@NotEmpty(message = ValidationMessage.Address.ADDRESS_CLIENT_ID_NOT_EMPTY)
	@NotNull(message= ValidationMessage.Address.ADDRESS_CLIENT_ID_NOT_NULL)
	private Long clientId;
	
	
	
}
