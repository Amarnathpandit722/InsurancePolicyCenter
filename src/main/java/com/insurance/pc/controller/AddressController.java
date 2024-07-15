package com.insurance.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.pc.dto.AddressDto;
import com.insurance.pc.dto.request.address.CreateAddressRequest;
import com.insurance.pc.dto.request.address.UpdateAddresRequest;
import com.insurance.pc.service.AddressService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/adress/pc")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/create-new-address")
	public ResponseEntity<Void> createAddress(@Valid @RequestBody CreateAddressRequest request) {
		try {
			addressService.createAddress(request);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateAddress(@PathVariable String id, @Valid @RequestBody UpdateAddresRequest request) {
		try {
			addressService.updateAddress(id, request);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable String id) {
		try {
			addressService.deleteAddress(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressDto> findAddressById(@PathVariable String id) {
		AddressDto addressDto = addressService.findAddressById(id);
		if (addressDto != null) {
			return new ResponseEntity<>(addressDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<List<AddressDto>> findAllAddresses() {
		List<AddressDto> addressDtos = addressService.findAllAddresses();
		if (!addressDtos.isEmpty()) {
			return new ResponseEntity<>(addressDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<AddressDto>> findAddressByClientId(@PathVariable String clientId) {
		List<AddressDto> addressDtos = addressService.findAddressByClientId(clientId);
		if (!addressDtos.isEmpty()) {
			return new ResponseEntity<>(addressDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/policy/{policyNumber}")
	public ResponseEntity<List<AddressDto>> findByAddressByPolicyNumber(@PathVariable String policyNumber) {
		List<AddressDto> addressDtos = addressService.findByAddressByPolicyNumber(policyNumber);
		if (!addressDtos.isEmpty()) {
			return new ResponseEntity<>(addressDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
