package com.outing.api.client.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.mapper.ClientMapper;
import com.outing.api.client.repositories.ClientRepository;

@Service
@Transactional(readOnly = true)
public class ClientService {

	private final ClientRepository clientRepository;

	private final ClientMapper clientMapper;

	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@Transactional
	public ClientResponse createClient(ClientRequest request) {
		var client = clientMapper.toEntity(request);
		return clientMapper.toResponse(clientRepository.save(client));
	}

	public List<ClientResponse> getClients() {
		var clients = clientRepository.findAllByDeletedFalse();
		return clientMapper.toResponse(clients);
	}

	public ClientResponse findUserById(int id) {
		var clientEntity = clientRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found " + id));
		return clientMapper.toResponse(clientEntity);
	}

	@Transactional
	public ClientResponse updateClient(int id, ClientRequest request) {
		var clientEntity = clientRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found " + id));
		clientMapper.updateEntity(request, clientEntity);
		return clientMapper.toResponse(clientRepository.save(clientEntity));
	}

	@Transactional
	public void deleteClient(int id) {
		var clientEntity = clientRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found " + id));
		clientEntity.setDeleted(true);
		clientRepository.save(clientEntity);
	}
}
