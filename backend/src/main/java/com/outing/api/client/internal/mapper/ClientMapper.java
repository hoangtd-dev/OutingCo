package com.outing.api.client.internal.mapper;

import org.mapstruct.Mapper;

import com.outing.api.client.api.dto.AddressRequest;
import com.outing.api.client.api.dto.ClientRequest;
import com.outing.api.client.api.dto.ClientResponse;
import com.outing.api.client.internal.entities.Client;
import com.outing.api.client.internal.entities.ClientAddress;

@Mapper(componentModel = "spring")
public interface ClientMapper {

	ClientResponse toResponse(Client client);

	Client toEntity(ClientRequest request);

	ClientAddress toAddress(AddressRequest request);

	default void updateEntity(ClientRequest request, Client client) {
		client.updateDetails(request.firstName(), request.lastName(), request.dateOfBirth(),
				request.phoneNumber(), toAddress(request.address()));
	}
}
