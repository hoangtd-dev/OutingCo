package com.outing.api.client.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.entities.Client;
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
	public ClientResponse create(ClientRequest request) {
		Client client = clientMapper.toEntity(request);
		return clientMapper.toResponse(clientRepository.save(client));
	}

	public List<ClientResponse> findAll() {
		return clientMapper.toResponse(clientRepository.findAllByDeletedFalse());
	}

	public ClientResponse findById(int id) {
		return clientMapper.toResponse(requireClient(id));
	}

	@Transactional
	public ClientResponse update(int id, ClientRequest request) {
		Client client = requireClient(id);
		clientMapper.updateEntity(request, client);
		return clientMapper.toResponse(clientRepository.save(client));
	}

	@Transactional
	public void deleteClient(int id) {
		Client client = requireClient(id);
		client.setDeleted(true);
		clientRepository.save(client);
	}

	private Client requireClient(int id) {
		return clientRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found " + id));
	}
}
