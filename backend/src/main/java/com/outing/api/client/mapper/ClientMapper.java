package com.outing.api.client.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.entities.User;
import com.outing.api.client.dto.AddressRequest;
import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.entities.Client;
import com.outing.api.shared.entities.Address;

@Component
public class ClientMapper {

	public ClientResponse toResponse(Client client) {
		return new ClientResponse(
				client.getId(),
				client.getCaseManager().getId(),
				client.getFirstName(),
				client.getLastName(),
				client.getEmail(),
				toAddressRequest(client.getAddress()),
				client.getDateOfBirth(),
				client.getClientNumber(),
				client.getPhone(),
				client.getEmergencyContactName(),
				client.getEmergencyContactRelationship(),
				client.getEmergencyContactPhonePrimary(),
				client.getIsActive());
	}

	public Client toEntity(ClientRequest request) {
		Client client = new Client();
		User caseManager = new User();
		caseManager.setId(request.caseManagerId());
		client.setCaseManager(caseManager);
		client.setFirstName(request.firstName());
		client.setLastName(request.lastName());
		client.setEmail(request.email());
		client.setAddress(toAddress(request.address()));
		client.setDateOfBirth(request.dateOfBirth());
		client.setClientNumber(request.clientNumber());
		client.setPhone(request.phone());
		client.setEmergencyContactName(request.emergencyContactName());
		client.setEmergencyContactRelationship(request.emergencyContactRelationship());
		client.setEmergencyContactPhonePrimary(request.emergencyContactPhonePrimary());
		if (request.isActive() != null) {
			client.setIsActive(request.isActive());
		}
		return client;
	}

	private Address toAddress(AddressRequest request) {
		if (request == null) {
			return null;
		}
		Address address = new Address();
		address.setAddressNumber(request.addressNumber());
		address.setAddressLine(request.addressLine());
		address.setCity(request.city());
		address.setState(request.state());
		address.setPostcode(request.postcode());
		address.setCountry(request.country());
		return address;
	}

	private AddressRequest toAddressRequest(Address address) {
		if (address == null) {
			return null;
		}
		return new AddressRequest(
				address.getAddressNumber(),
				address.getAddressLine(),
				address.getCity(),
				address.getState(),
				address.getPostcode(),
				address.getCountry());
	}
}
