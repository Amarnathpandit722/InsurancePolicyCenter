package com.insurance.pc.dto;


import java.util.List;
import java.util.Set;

public record ClientDto(
		String id,
		String firstName,
		String lastName,
		String email,
		String phoneNumber,
		String dateOfBirth,
		Set<InsurancePolicyDto> clientPolicies,
		List<AddressDto> addressList
		
		
		
		
		) {



}
