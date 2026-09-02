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

import com.outing.api.event.dto.requests.EventRouteStopRequest;
import com.outing.api.event.dto.responses.EventRouteStopResponse;
import com.outing.api.event.entities.EventRouteStop;
import com.outing.api.event.mapper.EventRouteStopMapper;
import com.outing.api.event.repositories.EventRouteStopRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/route-stops")
public class EventRouteStopController {

	private final EventRouteStopRepository eventRouteStopRepository;
	private final EventRouteStopMapper mapper;

	public EventRouteStopController(EventRouteStopRepository eventRouteStopRepository, EventRouteStopMapper mapper) {
		this.eventRouteStopRepository = eventRouteStopRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventRouteStopResponse>> getRouteStops(Pageable pageable) {
		return ResponseEntity.ok(eventRouteStopRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventRouteStopResponse> getRouteStop(@PathVariable int id) {
		EventRouteStop eventRouteStop = eventRouteStopRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventRouteStop not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(eventRouteStop));
	}

	@PostMapping
	public ResponseEntity<Void> createRouteStop(@Valid @RequestBody EventRouteStopRequest request) {
		eventRouteStopRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRouteStop(@PathVariable int id) {
		EventRouteStop eventRouteStop = eventRouteStopRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventRouteStop not found: " + id));
		eventRouteStop.setIsDeleted(true);
		eventRouteStopRepository.save(eventRouteStop);
		return ResponseEntity.noContent().build();
	}
}
