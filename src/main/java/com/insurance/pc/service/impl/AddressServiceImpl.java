package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.pc.dto.AddressDto;
import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.converter.AddressDtoConverter;
import com.insurance.pc.dto.request.address.CreateAddressRequest;
import com.insurance.pc.dto.request.address.UpdateAddresRequest;
import com.insurance.pc.exception.AddressNotFoundException;
import com.insurance.pc.exception.UnableToAddUpdateAdressException;
import com.insurance.pc.model.Address;
import com.insurance.pc.repository.AddressRepository;
import com.insurance.pc.service.AddressService;
import com.insurance.pc.service.InsurancePolicyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressDtoConverter addressDtoConverter;
	@Autowired
	private InsurancePolicyService insurancePolicyService;

	@Override
	public void createAddress(CreateAddressRequest request) {
		if (request == null) {
			throw new UnableToAddUpdateAdressException("Unable to Save Address");
		}
		Address address = new Address();

		AddressDtoConverter.setAddressProperties(address, request);

		addressRepository.save(address);

	}

	@Override
	public void updateAddress(String id, UpdateAddresRequest request) {
		if (request == null) {
			throw new UnableToAddUpdateAdressException("Unable to Update Address");
		}

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new AddressNotFoundException("Address not found with ID: " + id));

		AddressDtoConverter.setAddressProperties(address, request);

		addressRepository.save(address);
	}

	@Override
	public void deleteAddress(String id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);

		if (optionalAddress.isEmpty()) {
			throw new AddressNotFoundException("Address NotFound with Exception with  Id : " + id);
		}
		addressRepository.delete(optionalAddress.get());
	}

	@Override
	public AddressDto findAddressById(String id) {

		Optional<Address> optionalAddress = addressRepository.findById(id);

		if (optionalAddress.isEmpty()) {
			throw new AddressNotFoundException("Address NotFound with Exception with  Id : " + id);
		}

		return addressDtoConverter.convert(optionalAddress.get());
	}

	@Override
	public List<AddressDto> findAllAddresses() {
		List<Address> address = addressRepository.findAll();
		return addressDtoConverter.convert(address);
	}

	@Override
	public List<AddressDto> findAddressByClientId(String clientId) {

		List<Address> addressList = addressRepository.findByClient(clientId);
		return addressDtoConverter.convert(addressList);
	}

	@Override
	public List<AddressDto> findByAddressByPolicyNumber(String policyNumber) {

		InsurancePolicyDto insurancePolicyDto = insurancePolicyService.getPolicyByPolicyNumber(policyNumber);

		String clientId = insurancePolicyDto.clientId();

		List<Address> addressList = addressRepository.findByClient(clientId);
		return addressDtoConverter.convert(addressList);
	}

}
