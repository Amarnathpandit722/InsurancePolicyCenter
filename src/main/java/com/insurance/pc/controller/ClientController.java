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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.pc.dto.ClientDto;
import com.insurance.pc.dto.request.client.CreateClientRequest;
import com.insurance.pc.dto.request.client.UpdateClientRequest;
import com.insurance.pc.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/client/pc")
@CrossOrigin
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping("/create-new-client")
	public ResponseEntity<ClientDto> createClientInfo(@Valid @RequestBody CreateClientRequest request) {
		try {
			ClientDto clientDto = clientService.createClientInfo(request);
			return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ClientDto> updateClientInfo(@PathVariable String id,
			@Valid @RequestBody UpdateClientRequest request) {
		try {
			ClientDto clientDto = clientService.updateClientInfo(id, request);
			return new ResponseEntity<>(clientDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClientById(@PathVariable String id) {
		ClientDto clientDto = clientService.getClientById(id);
		if (clientDto != null) {
			return new ResponseEntity<>(clientDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping
	public ResponseEntity<List<ClientDto>> getListOfClient() {
		List<ClientDto> clientDtos = clientService.getListOfClient();
		if (!clientDtos.isEmpty()) {
			return new ResponseEntity<>(clientDtos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClientInfo(@PathVariable String id) {
		try {
			clientService.deleteClientInfo(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/name")
	public ResponseEntity<ClientDto> getClientByfirstNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String policyNumber) {
		ClientDto clientDto = clientService.getClientByfirstNameAndLastName(firstName, lastName, policyNumber);
		if (clientDto != null) {
			return new ResponseEntity<>(clientDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/policy-number/{policyNumber}")
	public ResponseEntity<ClientDto> getClientDtoByPolicyNumber(@PathVariable String policyNumber) {
		ClientDto clientDto = clientService.getClientDtoByPolicyNumber(policyNumber);
		if (clientDto != null) {
			return new ResponseEntity<>(clientDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
