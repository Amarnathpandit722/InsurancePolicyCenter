package com.insurance.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.pc.dto.request.broker.BrokerRequest;
import com.insurance.pc.model.Broker;
import com.insurance.pc.service.IBrokerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/brokers")
@CrossOrigin
public class BrokerController {

	@Autowired
	private IBrokerService brokerService;

	@PostMapping("/add")
	public ResponseEntity<Broker> createBroker(@Valid @ModelAttribute BrokerRequest broker) {
		Broker createdBroker = brokerService.createBroker(broker);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBroker);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Broker> getBrokerById(@PathVariable String id) {
		Broker broker = brokerService.getBrokerById(id);
		return ResponseEntity.ok(broker);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Broker>> getAllBrokers() {
		List<Broker> brokers = brokerService.getAllBrokers();
		return ResponseEntity.ok(brokers);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Broker> updateBroker(@PathVariable String id, @Valid @ModelAttribute BrokerRequest broker) {
		Broker updatedBroker = brokerService.updateBroker(id, broker);
		return ResponseEntity.ok(updatedBroker);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBroker(@PathVariable String id) {
		brokerService.deleteBroker(id);
		return ResponseEntity.noContent().build();
	}
}
