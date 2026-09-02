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

import com.outing.api.notification.dto.requests.EmailGroupMemberRequest;
import com.outing.api.notification.dto.responses.EmailGroupMemberResponse;
import com.outing.api.notification.entities.EmailGroupMember;
import com.outing.api.notification.entities.compositeKey.EmailGroupMemberId;
import com.outing.api.notification.mapper.EmailGroupMemberMapper;
import com.outing.api.notification.repositories.EmailGroupMemberRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/email-group-members")
public class EmailGroupMemberController {

	private final EmailGroupMemberRepository emailGroupMemberRepository;
	private final EmailGroupMemberMapper mapper;

	public EmailGroupMemberController(EmailGroupMemberRepository emailGroupMemberRepository, EmailGroupMemberMapper mapper) {
		this.emailGroupMemberRepository = emailGroupMemberRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EmailGroupMemberResponse>> getEmailGroupMembers(Pageable pageable) {
		return ResponseEntity.ok(emailGroupMemberRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{groupId}/{userId}")
	public ResponseEntity<EmailGroupMemberResponse> getEmailGroupMember(@PathVariable int groupId, @PathVariable int userId) {
		EmailGroupMember emailGroupMember = emailGroupMemberRepository.findById(new EmailGroupMemberId(groupId, userId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"EmailGroupMember not found: " + groupId + "/" + userId));
		return ResponseEntity.ok(mapper.toResponse(emailGroupMember));
	}

	@PostMapping
	public ResponseEntity<Void> createEmailGroupMember(@Valid @RequestBody EmailGroupMemberRequest request) {
		emailGroupMemberRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{groupId}/{userId}")
	public ResponseEntity<Void> deleteEmailGroupMember(@PathVariable int groupId, @PathVariable int userId) {
		EmailGroupMemberId id = new EmailGroupMemberId(groupId, userId);
		if (!emailGroupMemberRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailGroupMember not found: " + groupId + "/" + userId);
		}
		emailGroupMemberRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
