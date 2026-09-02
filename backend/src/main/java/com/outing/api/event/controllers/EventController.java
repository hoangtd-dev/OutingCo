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

import com.outing.api.event.dto.requests.EventRequest;
import com.outing.api.event.dto.responses.EventResponse;
import com.outing.api.event.entities.Event;
import com.outing.api.event.mapper.EventMapper;
import com.outing.api.event.repositories.EventRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	private final EventRepository eventRepository;
	private final EventMapper mapper;

	public EventController(EventRepository eventRepository, EventMapper mapper) {
		this.eventRepository = eventRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventResponse>> getEvents(Pageable pageable) {
		return ResponseEntity.ok(eventRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventResponse> getEvent(@PathVariable int id) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(event));
	}

	@PostMapping
	public ResponseEntity<Void> createEvent(@Valid @RequestBody EventRequest request) {
		eventRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found: " + id));
		event.setIsDeleted(true);
		eventRepository.save(event);
		return ResponseEntity.noContent().build();
	}
}
