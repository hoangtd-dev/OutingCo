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

import com.outing.api.event.dto.requests.EventSessionRequest;
import com.outing.api.event.dto.responses.EventSessionResponse;
import com.outing.api.event.entities.EventSession;
import com.outing.api.event.mapper.EventSessionMapper;
import com.outing.api.event.repositories.EventSessionRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/event-sessions")
public class EventSessionController {

	private final EventSessionRepository eventSessionRepository;
	private final EventSessionMapper mapper;

	public EventSessionController(EventSessionRepository eventSessionRepository, EventSessionMapper mapper) {
		this.eventSessionRepository = eventSessionRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventSessionResponse>> getEventSessions(Pageable pageable) {
		return ResponseEntity.ok(eventSessionRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventSessionResponse> getEventSession(@PathVariable int id) {
		EventSession eventSession = eventSessionRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventSession not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(eventSession));
	}

	@PostMapping
	public ResponseEntity<Void> createEventSession(@Valid @RequestBody EventSessionRequest request) {
		eventSessionRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEventSession(@PathVariable int id) {
		EventSession eventSession = eventSessionRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventSession not found: " + id));
		eventSession.setIsDeleted(true);
		eventSessionRepository.save(eventSession);
		return ResponseEntity.noContent().build();
	}
}
