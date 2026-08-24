package com.outing.api.client.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.outing.api.client.api.dto.AddressRequest;
import com.outing.api.client.api.dto.ClientRequest;
import com.outing.api.client.api.dto.ClientResponse;
import com.outing.api.client.entities.Client;
import com.outing.api.client.entities.ClientAddress;

@Mapper(componentModel = "spring")
public interface ClientMapper {

	ClientResponse toResponse(Client client);

	List<ClientResponse> toResponse(List<Client> clients);

	ClientAddress toAddress(AddressRequest request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Client toEntity(ClientRequest request);

	@InheritConfiguration(name = "toEntity")
	void updateEntity(ClientRequest request, @MappingTarget Client client);
}
