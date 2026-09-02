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

import com.outing.api.event.dto.requests.EventEmailRequest;
import com.outing.api.event.dto.responses.EventEmailResponse;
import com.outing.api.event.entities.EventEmail;
import com.outing.api.event.mapper.EventEmailMapper;
import com.outing.api.event.repositories.EventEmailRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/event-emails")
public class EventEmailController {

	private final EventEmailRepository eventEmailRepository;
	private final EventEmailMapper mapper;

	public EventEmailController(EventEmailRepository eventEmailRepository, EventEmailMapper mapper) {
		this.eventEmailRepository = eventEmailRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventEmailResponse>> getEventEmails(Pageable pageable) {
		return ResponseEntity.ok(eventEmailRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventEmailResponse> getEventEmail(@PathVariable int id) {
		EventEmail eventEmail = eventEmailRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventEmail not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(eventEmail));
	}

	@PostMapping
	public ResponseEntity<Void> createEventEmail(@Valid @RequestBody EventEmailRequest request) {
		eventEmailRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEventEmail(@PathVariable int id) {
		EventEmail eventEmail = eventEmailRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EventEmail not found: " + id));
		eventEmail.setIsDeleted(true);
		eventEmailRepository.save(eventEmail);
		return ResponseEntity.noContent().build();
	}
}
