package com.insurance.pc.dto.request.address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BaseAddressRequest {

	@NotEmpty(message = "${ValidationMessages.Address.STREET_NOT_EMPTY}")
	@NotNull(message = "${ValidationMessages.Address.STREET_NOT_NULL}")
	private String street;

	@NotEmpty(message = "${ValidationMessages.Address.CITY_NOT_EMPTY}")
	@NotNull(message = "${ValidationMessages.Address.CITY_NOT_NULL}")
	private String city;

	@NotEmpty(message = "${ValidationMessages.Address.STATE_NOT_EMPTY}")
	@NotNull(message = "${ValidationMessages.Address.STATE_NOT_NULL}")
	private String state;

	@NotEmpty(message = "${ValidationMessages.Address.ZIP_CODE_NOT_EMPTY}")
	@NotNull(message = "${ValidationMessages.Address.ZIP_CODE_NOT_NULL}")
	private String zipCode;

	@NotEmpty(message = "${ValidationMessages.Address.ADDRESS_TYPE_NOT_EMPTY}")
	@NotNull(message = "${ValidationMessages.Address.ADDRESS_TYPE_NOT_NULL}")
	private String addressType;


}
