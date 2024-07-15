package com.insurance.pc.dto.request.client;

import java.util.List;
import java.util.Set;

import com.insurance.pc.dto.request.address.CreateAddressRequest;
import com.insurance.pc.dto.request.insurance.CreateRequestInsurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseClientRequest {

	private List<CreateAddressRequest>  addressDto;
	
	private Set<CreateRequestInsurance> insurancePolicyRequest;
	
	
	
	
}
