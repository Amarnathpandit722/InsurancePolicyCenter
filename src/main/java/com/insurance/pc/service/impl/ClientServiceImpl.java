package com.insurance.pc.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.pc.dto.ClientDto;
import com.insurance.pc.dto.InsurancePolicyDto;
import com.insurance.pc.dto.converter.ClientDtoConverter;
import com.insurance.pc.dto.request.client.CreateClientRequest;
import com.insurance.pc.dto.request.client.UpdateClientRequest;
import com.insurance.pc.exception.ClientInformationNotAvailable;
import com.insurance.pc.exception.ClientRequestAddandUpdateException;
import com.insurance.pc.model.Address;
import com.insurance.pc.model.Broker;
import com.insurance.pc.model.Client;
import com.insurance.pc.model.InsurancePolicy;
import com.insurance.pc.repository.AddressRepository;
import com.insurance.pc.repository.ClientRepository;
import com.insurance.pc.service.ClientService;
import com.insurance.pc.service.IBrokerService;
import com.insurance.pc.service.InsurancePolicyService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl  implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientDtoConverter clientDtoConverter;
	@Autowired
	private  IBrokerService brokerService;


	@Override
    @Transactional(rollbackFor = { ClientRequestAddandUpdateException.class, Exception.class })
	public ClientDto createClientInfo(CreateClientRequest request) {
		if(request==null) {
			throw new ClientRequestAddandUpdateException("Client is Not able to Save data : ");
		}
		Client client = new Client();
		
		ClientDtoConverter.setClientDtoInformation(client,request);
		
		String brokerId = request.getInsurancePolicyRequest()
                .stream()
                .findFirst()
                .map(policy -> policy.getBrokerId())
                .orElseThrow(()-> new ClientRequestAddandUpdateException("unable to save data"));
			Broker broker = brokerService.getBrokerById(brokerId);
	        client.getPolicies().stream().forEach(policy -> policy.setBroker(broker));
	        
		
		Client newclient = clientRepository.save(client);
		

		
		String clientId = newclient.getId();
		Set<InsurancePolicy> policy = newclient.getPolicies();
		String policyId = policy.stream().findFirst().map(pol -> pol.getId()).orElse(null);
		
		insurancePolicyService.updateClientId(clientId, policyId);
		
		client.getAddressList().forEach(address -> {
			Address adress = addressRepository.findById(address.getId()).orElse(null);
//		    String addressClientId = address.getClient().getId();
		    adress.setClient(newclient);
		    addressRepository.save(adress);
		    System.out.println("Client ID for Address " + address.getId() + ": "+address.getClient().getId() );
		});
		
		log.info("CLient ID {}",clientId);
		ClientDto	clientDto=	clientDtoConverter.convert(newclient);
		
		
		return clientDto;
	}

	@Override
	public ClientDto updateClientInfo(String id, UpdateClientRequest request) {
		
		return null;
	}

	@Override
	public ClientDto getClientById(String id) {
	

		Optional<Client> optionalClient = clientRepository.findById(id);
		
		return clientDtoConverter.convert(optionalClient.get());
		
	}

	@Override
	public List<ClientDto> getListOfClient() {
		List<Client> clientList= clientRepository.findAll();
		return clientDtoConverter.convert(clientList);
	}

	@Override
	public void deleteClientInfo(String id) {
		

		Optional<Client> optionalClient = clientRepository.findById(id);
			if(optionalClient.isEmpty()) {
				throw new ClientInformationNotAvailable("Cleint Id Information Not Avaialble with ID : "+id);
			}
		clientRepository.delete(optionalClient.get());
			
	}

	@Override
	public ClientDto getClientByfirstNameAndLastName(String firstName, String lastName, String policyNumber) {
		
		return null;
	}

	@Override
	public ClientDto getClientDtoByPolicyNumber(String policyNumber) {
		InsurancePolicyDto insurace=	insurancePolicyService.getPolicyByPolicyNumber(policyNumber);
		String clientId= insurace.clientId();
		Client client = clientRepository.findById(clientId).orElse(null);
		return clientDtoConverter.convert(client);
	}
	
	
	
	
	
	
}
