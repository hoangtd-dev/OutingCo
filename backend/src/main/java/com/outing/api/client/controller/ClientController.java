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

import com.outing.api.client.dto.ClientResponse;
import com.outing.api.client.dto.ClientRequest;
import com.outing.api.client.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ClientRequest request) {
		clientService.createClient(request);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<List<ClientResponse>> findAll() {
		return ResponseEntity.ok(clientService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClientResponse> findById(@PathVariable int id) {
		return ResponseEntity.ok(clientService.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClientResponse> update(@PathVariable int id, @Valid @RequestBody ClientRequest request) {
		return ResponseEntity.ok(clientService.updateClient(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		clientService.deleteClient(id);
		return ResponseEntity.noContent().build();
	}
}
