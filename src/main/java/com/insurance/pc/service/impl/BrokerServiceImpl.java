package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.pc.dto.request.broker.BrokerRequest;
import com.insurance.pc.model.Broker;
import com.insurance.pc.repository.BrokerRespository;
import com.insurance.pc.service.IBrokerService;

import jakarta.validation.Valid;

@Service
public class BrokerServiceImpl implements IBrokerService {

	@Autowired
	private BrokerRespository brokerRepository;

	public Broker createBroker(@Valid BrokerRequest brokerRequest) {
		Broker broker = new Broker();
		broker.setName(brokerRequest.getName());
		broker.setEmail(brokerRequest.getEmail());
		broker.setPhoneNumber(brokerRequest.getPhoneNumber());
		return brokerRepository.save(broker);
	}

	public Broker getBrokerById(String id) {
		Optional<Broker> optionalBroker = brokerRepository.findById(id);
		return optionalBroker.orElse(null);
	}

	public List<Broker> getAllBrokers() {
		return brokerRepository.findAll();
	}

	public Broker updateBroker(String id, @Valid BrokerRequest brokerRequest) {
		Optional<Broker> optionalBroker = brokerRepository.findById(id);
		if (optionalBroker.isPresent()) {
			Broker broker = optionalBroker.get();
			broker.setName(brokerRequest.getName());
			broker.setEmail(brokerRequest.getEmail());
			broker.setPhoneNumber(brokerRequest.getPhoneNumber());
			return brokerRepository.save(broker);
		}
		return null; // Or throw an exception if necessary
	}

	public void deleteBroker(String id) {
		brokerRepository.deleteById(id);
	}
}
