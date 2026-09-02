package com.outing.api.client.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/clients")
public class ClientController {

	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	public ClientController(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	@GetMapping
	public ResponseEntity<Page<ClientResponse>> getClients(Pageable pageable) {
		return ResponseEntity.ok(clientRepository.findAll(pageable).map(clientMapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientResponse> getClient(@PathVariable int id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found: " + id));
		return ResponseEntity.ok(clientMapper.toResponse(client));
	}

	@PostMapping
	public ResponseEntity<Void> createClient(@Valid @RequestBody ClientRequest request) {
		clientRepository.save(clientMapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable int id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found: " + id));
		client.setIsDeleted(true);
		clientRepository.save(client);
		return ResponseEntity.noContent().build();
	}
}
