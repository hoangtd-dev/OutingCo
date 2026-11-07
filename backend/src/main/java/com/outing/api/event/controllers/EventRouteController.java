package com.outing.api.event.controllers;

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

import com.outing.api.event.dto.requests.EventRouteRequest;
import com.outing.api.event.dto.responses.EventRouteResponse;
import com.outing.api.event.entities.EventRoute;
import com.outing.api.event.mapper.EventRouteMapper;
import com.outing.api.event.repositories.EventRouteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/routes")
public class EventRouteController {

	private final EventRouteRepository eventRouteRepository;
	private final EventRouteMapper mapper;

	public EventRouteController(EventRouteRepository eventRouteRepository, EventRouteMapper mapper) {
		this.eventRouteRepository = eventRouteRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventRouteResponse>> getRoutes(Pageable pageable) {
		return ResponseEntity.ok(eventRouteRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventRouteResponse> getRoute(@PathVariable int id) {
		EventRoute eventRoute = eventRouteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventRoute not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(eventRoute));
	}

	@PostMapping
	public ResponseEntity<Void> createRoute(@Valid @RequestBody EventRouteRequest request) {
		eventRouteRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoute(@PathVariable int id) {
		EventRoute eventRoute = eventRouteRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventRoute not found: " + id));
		eventRoute.setIsDeleted(true);
		eventRouteRepository.save(eventRoute);
		return ResponseEntity.noContent().build();
	}
}
