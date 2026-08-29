package com.outing.api.client.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.entities.Client;
import com.outing.api.client.mapper.ClientMapper;
import com.outing.api.client.repositories.ClientRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientRepository clientRepository;

	private final ClientMapper clientMapper;

	public ClientController(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@PostMapping
	public ResponseEntity<Void> createClient(@Valid @RequestBody ClientRequest request) {
		Client clientEntity = clientMapper.toEntity(request);
		clientRepository.save(clientEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<ClientResponse>> getClients() {
		List<Client> clients = clientRepository.findAllByDeletedFalse();
		return ResponseEntity.ok(clientMapper.toResponse(clients));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientResponse> findUserById(@PathVariable int id) {
		Client clientEntity = requireClient(id);
		return ResponseEntity.ok(clientMapper.toResponse(clientEntity));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientResponse> updateClient(@PathVariable int id, @Valid @RequestBody ClientRequest request) {
		Client clientEntity = requireClient(id);
		clientMapper.updateEntity(request, clientEntity);
		return ResponseEntity.ok(clientMapper.toResponse(clientRepository.save(clientEntity)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable int id) {
		Client clientEntity = requireClient(id);
		clientEntity.setDeleted(true);
		clientRepository.save(clientEntity);
		return ResponseEntity.noContent().build();
	}

	private Client requireClient(int id) {
		return clientRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found " + id));
	}
}
