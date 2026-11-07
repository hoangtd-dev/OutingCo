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

import com.outing.api.event.dto.requests.EventEmailGroupRequest;
import com.outing.api.event.dto.responses.EventEmailGroupResponse;
import com.outing.api.event.entities.EventEmailGroup;
import com.outing.api.event.entities.compositeKey.EventEmailGroupId;
import com.outing.api.event.mapper.EventEmailGroupMapper;
import com.outing.api.event.repositories.EventEmailGroupRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/event-email-groups")
public class EventEmailGroupController {

	private final EventEmailGroupRepository eventEmailGroupRepository;
	private final EventEmailGroupMapper mapper;

	public EventEmailGroupController(EventEmailGroupRepository eventEmailGroupRepository, EventEmailGroupMapper mapper) {
		this.eventEmailGroupRepository = eventEmailGroupRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EventEmailGroupResponse>> getEventEmailGroups(Pageable pageable) {
		return ResponseEntity.ok(eventEmailGroupRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{eventEmailId}/{emailGroupId}")
	public ResponseEntity<EventEmailGroupResponse> getEventEmailGroup(@PathVariable int eventEmailId, @PathVariable int emailGroupId) {
		EventEmailGroup eventEmailGroup = eventEmailGroupRepository.findById(new EventEmailGroupId(eventEmailId, emailGroupId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"EventEmailGroup not found: " + eventEmailId + "/" + emailGroupId));
		return ResponseEntity.ok(mapper.toResponse(eventEmailGroup));
	}

	@PostMapping
	public ResponseEntity<Void> createEventEmailGroup(@Valid @RequestBody EventEmailGroupRequest request) {
		eventEmailGroupRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{eventEmailId}/{emailGroupId}")
	public ResponseEntity<Void> deleteEventEmailGroup(@PathVariable int eventEmailId, @PathVariable int emailGroupId) {
		EventEmailGroupId id = new EventEmailGroupId(eventEmailId, emailGroupId);
		if (!eventEmailGroupRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"EventEmailGroup not found: " + eventEmailId + "/" + emailGroupId);
		}
		eventEmailGroupRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
