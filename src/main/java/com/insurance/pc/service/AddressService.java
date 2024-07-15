package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.AddressDto;
import com.insurance.pc.dto.request.address.CreateAddressRequest;
import com.insurance.pc.dto.request.address.UpdateAddresRequest;

public interface AddressService {

	
	public void createAddress(CreateAddressRequest request);

	public void updateAddress(String id, UpdateAddresRequest request);
	
	public void deleteAddress(String id);

	public AddressDto findAddressById(String id);

	public List<AddressDto> findAllAddresses();
	
	public List<AddressDto> findAddressByClientId(String  clientId);
	
	public List<AddressDto> findByAddressByPolicyNumber(String policyNumber);
	
}
