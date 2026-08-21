package com.outing.api.client.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.outing.api.client.api.dto.ClientResponse;
import com.outing.api.client.api.dto.ClientRequest;
import com.outing.api.client.internal.services.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

	private final ClientService clientService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientResponse create(@Valid @RequestBody ClientRequest request) {
		return clientService.create(request);
	}

	@GetMapping
	public List<ClientResponse> findAll() {
		return clientService.findAll();
	}

	@GetMapping("/{id}")
	public ClientResponse findById(@PathVariable int id) {
		return clientService.findById(id);
	}

	@PutMapping("/{id}")
	public ClientResponse update(@PathVariable int id, @Valid @RequestBody ClientRequest request) {
		return clientService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		clientService.delete(id);
	}
}
