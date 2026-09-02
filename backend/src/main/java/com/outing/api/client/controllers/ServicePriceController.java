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

import com.outing.api.client.dto.requests.ServicePriceRequest;
import com.outing.api.client.dto.responses.ServicePriceResponse;
import com.outing.api.client.entities.ServicePrice;
import com.outing.api.client.mapper.ServicePriceMapper;
import com.outing.api.client.repositories.ServicePriceRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/service-prices")
public class ServicePriceController {

	private final ServicePriceRepository servicePriceRepository;
	private final ServicePriceMapper mapper;

	public ServicePriceController(ServicePriceRepository servicePriceRepository, ServicePriceMapper mapper) {
		this.servicePriceRepository = servicePriceRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<ServicePriceResponse>> getServicePrices(Pageable pageable) {
		return ResponseEntity.ok(servicePriceRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ServicePriceResponse> getServicePrice(@PathVariable int id) {
		ServicePrice servicePrice = servicePriceRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServicePrice not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(servicePrice));
	}

	@PostMapping
	public ResponseEntity<Void> createServicePrice(@Valid @RequestBody ServicePriceRequest request) {
		servicePriceRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteServicePrice(@PathVariable int id) {
		ServicePrice servicePrice = servicePriceRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ServicePrice not found: " + id));
		servicePrice.setIsDeleted(true);
		servicePriceRepository.save(servicePrice);
		return ResponseEntity.noContent().build();
	}
}
