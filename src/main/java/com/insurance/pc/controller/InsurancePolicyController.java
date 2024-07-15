package com.insurance.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.request.insurance.CreateRequestInsurance;
import com.insurance.pc.dto.request.insurance.UpdateRequestInsurance;
import com.insurance.pc.service.InsurancePolicyService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user/pc")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService insurancePolicyService;

	@PostMapping("create-new-policy")
	public ResponseEntity<InsurancePolicyDto> createNewPolicy(@Valid @RequestBody CreateRequestInsurance request) {
		try {
			InsurancePolicyDto policyDto = insurancePolicyService.createNewPolicy(request);
			return new ResponseEntity<>(policyDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<InsurancePolicyDto> updateCreatedPolicy(@PathVariable String id,
			@Valid @RequestBody UpdateRequestInsurance request) {
		try {
			InsurancePolicyDto policyDto = insurancePolicyService.updateCreatedPolicy(id, request);
			return new ResponseEntity<>(policyDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<InsurancePolicyDto> getPolicyDetailsById(@PathVariable String id) {
		InsurancePolicyDto policyDto = insurancePolicyService.getPolicyDetailsById(id);
		if (policyDto != null) {
			return new ResponseEntity<>(policyDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<List<InsurancePolicyDto>> getAllPolicyDto() {
		List<InsurancePolicyDto> policyDtos = insurancePolicyService.getAllPolicyDto();
		if (!policyDtos.isEmpty()) {
			return new ResponseEntity<>(policyDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/policy-number/{policyNumber}")
	public ResponseEntity<InsurancePolicyDto> getPolicyByPolicyNumber(@PathVariable String policyNumber) {
		InsurancePolicyDto policyDto = insurancePolicyService.getPolicyByPolicyNumber(policyNumber);
		if (policyDto != null) {
			return new ResponseEntity<>(policyDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/national-id/{nationalId}")
	public ResponseEntity<List<InsurancePolicyDto>> getPolicyDetailsByNationalId(@PathVariable String nationalId) {
		List<InsurancePolicyDto> policyDtos = insurancePolicyService.getPolicyDetailsByNationalId(nationalId);
		if (!policyDtos.isEmpty()) {
			return new ResponseEntity<>(policyDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/broker-id/{brokerId}")
	public ResponseEntity<List<InsurancePolicyDto>> getPolicyDetialsByBrokerId(@PathVariable String brokerId) {
		List<InsurancePolicyDto> policyDtos = insurancePolicyService.getPolicyDetialsByBrokerId(brokerId);
		if (!policyDtos.isEmpty()) {
			return new ResponseEntity<>(policyDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/cancel/{id}")
	public ResponseEntity<InsurancePolicyDto> cancelPolicy(@PathVariable("id") String id) {
		InsurancePolicyDto policy = insurancePolicyService.cancelPolicy(id);
		return ResponseEntity.ok(policy);
	}

	@PutMapping("/renew/{id}")
	public ResponseEntity<InsurancePolicyDto> renewPolicy(@PathVariable("id") String id,
			@RequestParam("newEndDate") String newEndDate) {
		InsurancePolicyDto policy = insurancePolicyService.renewPolicy(id, newEndDate);
		return ResponseEntity.ok(policy);
	}

}
