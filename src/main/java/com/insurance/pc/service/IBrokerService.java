package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.request.broker.BrokerRequest;
import com.insurance.pc.model.Broker;

import jakarta.validation.Valid;

public interface IBrokerService {

	
	public Broker createBroker(@Valid BrokerRequest brokerRequest);
	
	public Broker getBrokerById(String id);
	
	 public List<Broker> getAllBrokers();
	
	 public Broker updateBroker(String id, @Valid BrokerRequest brokerRequest);
	
	 public void deleteBroker(String id);
	
	
	
}
