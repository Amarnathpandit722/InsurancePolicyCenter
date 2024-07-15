package com.insurance.pc.service;

import java.util.List;

import com.insurance.pc.dto.ClientDto;
import com.insurance.pc.dto.request.client.CreateClientRequest;
import com.insurance.pc.dto.request.client.UpdateClientRequest;

public interface ClientService {
	
	public ClientDto createClientInfo(CreateClientRequest request);
	
	public ClientDto updateClientInfo(String id, UpdateClientRequest request);
	
	public ClientDto getClientById(String id);
	
	public List<ClientDto> getListOfClient();
	
	public void deleteClientInfo(String id);
	
	public ClientDto getClientByfirstNameAndLastName(String firstName,String lastName,String policyNumber);	
	
	public ClientDto getClientDtoByPolicyNumber(String policyNumber);
	

	
	
	
	
	

}
