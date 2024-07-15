package com.insurance.pc.dto;




public record AddressDto(
		
		String id,
		String street,
		String city,
		String state,
		String zipCode,
		String addressType,
		String clientId
		) {

}
