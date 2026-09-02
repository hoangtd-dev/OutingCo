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

import com.outing.api.notification.dto.requests.EmailTemplateRequest;
import com.outing.api.notification.dto.responses.EmailTemplateResponse;
import com.outing.api.notification.entities.EmailTemplate;
import com.outing.api.notification.mapper.EmailTemplateMapper;
import com.outing.api.notification.repositories.EmailTemplateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/email-templates")
public class EmailTemplateController {

	private final EmailTemplateRepository emailTemplateRepository;
	private final EmailTemplateMapper mapper;

	public EmailTemplateController(EmailTemplateRepository emailTemplateRepository, EmailTemplateMapper mapper) {
		this.emailTemplateRepository = emailTemplateRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<EmailTemplateResponse>> getEmailTemplates(Pageable pageable) {
		return ResponseEntity.ok(emailTemplateRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmailTemplateResponse> getEmailTemplate(@PathVariable int id) {
		EmailTemplate emailTemplate = emailTemplateRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailTemplate not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(emailTemplate));
	}

	@PostMapping
	public ResponseEntity<Void> createEmailTemplate(@Valid @RequestBody EmailTemplateRequest request) {
		emailTemplateRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmailTemplate(@PathVariable int id) {
		EmailTemplate emailTemplate = emailTemplateRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmailTemplate not found: " + id));
		emailTemplate.setIsDeleted(true);
		emailTemplateRepository.save(emailTemplate);
		return ResponseEntity.noContent().build();
	}
}
