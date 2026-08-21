package com.outing.api.client.internal.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.outing.api.client.api.dto.ClientResponse;
import com.outing.api.client.api.dto.ClientRequest;
import com.outing.api.client.internal.entities.Client;
import com.outing.api.client.internal.mapper.ClientMapper;
import com.outing.api.client.internal.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

	private final ClientRepository clientRepository;

	private final ClientMapper clientMapper;

	@Transactional
	public ClientResponse create(ClientRequest request) {
		Client client = clientMapper.toEntity(request);
		return clientMapper.toResponse(clientRepository.save(client));
	}

	public List<ClientResponse> findAll() {
		return clientRepository.findAll().stream().map(clientMapper::toResponse).toList();
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
	public void delete(int id) {
		clientRepository.delete(requireClient(id));
	}

	private Client requireClient(int id) {
		return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
	}
}
