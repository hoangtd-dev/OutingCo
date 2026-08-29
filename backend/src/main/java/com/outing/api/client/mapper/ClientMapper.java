package com.outing.api.client.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.outing.api.client.dto.AddressRequest;
import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.entities.Client;
import com.outing.api.client.entities.ClientAddress;

@Component
public class ClientMapper {

	public ClientResponse toResponse(Client client) {
		return new ClientResponse(client.getId(), client.getFirstName(), client.getLastName());
	}

	public List<ClientResponse> toResponse(List<Client> clients) {
		return clients.stream().map(this::toResponse).toList();
	}

	public ClientAddress toAddress(AddressRequest request) {
		if (request == null) {
			return null;
		}
		ClientAddress address = new ClientAddress();
		address.setStreet(request.street());
		address.setSuburb(request.suburb());
		address.setState(request.state());
		address.setPostcode(request.postcode());
		return address;
	}

	public Client toEntity(ClientRequest request) {
		Client client = new Client();
		applyRequest(request, client);
		return client;
	}

	public void updateEntity(ClientRequest request, Client client) {
		applyRequest(request, client);
	}

	private void applyRequest(ClientRequest request, Client client) {
		client.setFirstName(request.firstName());
		client.setLastName(request.lastName());
		client.setDateOfBirth(request.dateOfBirth());
		client.setPhoneNumber(request.phoneNumber());
		client.setAddress(toAddress(request.address()));
	}
}
