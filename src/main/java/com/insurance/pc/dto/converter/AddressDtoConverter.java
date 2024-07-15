package com.insurance.pc.dto.converter;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.insurance.pc.dto.AddressDto;
import com.insurance.pc.dto.request.address.CreateAddressRequest;
import com.insurance.pc.dto.request.address.UpdateAddresRequest;
import com.insurance.pc.helper.AddressType;
import com.insurance.pc.model.Address;

@Component
public class AddressDtoConverter {

	public AddressDto convert(Address from) {
		return new AddressDto(from.getId(), from.getStreet(), from.getCity(), from.getState(), from.getZipCode(),
				from.getAddressType() != null ? from.getAddressType().name() : null, from.getClient()!=null ? from.getClient().getId():null);
	}

	public List<AddressDto> convert(List<Address> from) {
	    return from.stream()
	               .map(address -> new AddressDto(
	                   address.getId(),
	                   address.getStreet(),
	                   address.getCity(),
	                   address.getState(),
	                   address.getZipCode(),
	                   address.getAddressType() != null ? address.getAddressType().name() : null,
	                   address.getClient()!=null ? address.getClient().getId():null
	               ))
	               .collect(Collectors.toList());
	}


	public static void setAddressProperties(Address address, CreateAddressRequest request) {
		List<BiConsumer<Address, CreateAddressRequest>> setters = List.of(
				(a, r) -> a.setState(r.getState()),
				(a, r) -> a.setCity(r.getCity()), (a, r) -> a.setStreet(r.getStreet()), (a, r) -> {
					AddressType addressType = AddressType.valueOf(r.getAddressType().toUpperCase());
					a.setAddressType(addressType);
				}, (a, r) -> a.setZipCode(r.getZipCode()));

		setters.forEach(setter -> setter.accept(address, request));

	}

	public static void setAddressProperties(Address address, UpdateAddresRequest request) {
		List<BiConsumer<Address, UpdateAddresRequest>> setters = List.of((a, r) -> a.setState(r.getState()),
				(a, r) -> a.setCity(r.getCity()), (a, r) -> a.setStreet(r.getStreet()), (a, r) -> {
					AddressType addressType = AddressType.valueOf(r.getAddressType().toUpperCase());
					a.setAddressType(addressType);
				}, (a, r) -> a.setZipCode(r.getZipCode()));

		setters.forEach(setter -> setter.accept(address, request));

	}
}
