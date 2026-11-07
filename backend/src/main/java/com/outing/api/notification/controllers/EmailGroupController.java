package com.outing.api.notification.controllers;

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

import com.outing.api.notification.dto.requests.EmailGroupRequest;
import com.outing.api.notification.dto.responses.EmailGroupResponse;
import com.outing.api.notification.entities.EmailGroup;
import com.outing.api.notification.mapper.EmailGroupMapper;
import com.outing.api.notification.repositories.EmailGroupRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/email-groups")
public class EmailGroupController {

	private final EmailGroupRepository emailGroupRepository;
	private final EmailGroupMapper mapper;

	public EmailGroupController(EmailGroupRepository emailGroupRepository, EmailGroupMapper mapper) {
		this.emailGroupRepository = emailGroupRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EmailGroupResponse>> getEmailGroups(Pageable pageable) {
		return ResponseEntity.ok(emailGroupRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmailGroupResponse> getEmailGroup(@PathVariable int id) {
		EmailGroup emailGroup = emailGroupRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailGroup not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(emailGroup));
	}

	@PostMapping
	public ResponseEntity<Void> createEmailGroup(@Valid @RequestBody EmailGroupRequest request) {
		emailGroupRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmailGroup(@PathVariable int id) {
		EmailGroup emailGroup = emailGroupRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailGroup not found: " + id));
		emailGroup.setIsDeleted(true);
		emailGroupRepository.save(emailGroup);
		return ResponseEntity.noContent().build();
	}
}
