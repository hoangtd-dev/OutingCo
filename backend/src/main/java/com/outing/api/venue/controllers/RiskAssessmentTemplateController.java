package com.outing.api.venue.controllers;

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

import com.outing.api.venue.dto.requests.RiskAssessmentTemplateRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentTemplateResponse;
import com.outing.api.venue.entities.RiskAssessmentTemplate;
import com.outing.api.venue.mapper.RiskAssessmentTemplateMapper;
import com.outing.api.venue.repositories.RiskAssessmentTemplateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/risk-assessment-templates")
public class RiskAssessmentTemplateController {

	private final RiskAssessmentTemplateRepository riskAssessmentTemplateRepository;
	private final RiskAssessmentTemplateMapper mapper;

	public RiskAssessmentTemplateController(RiskAssessmentTemplateRepository riskAssessmentTemplateRepository,
			RiskAssessmentTemplateMapper mapper) {
		this.riskAssessmentTemplateRepository = riskAssessmentTemplateRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RiskAssessmentTemplateResponse>> getRiskAssessmentTemplates(Pageable pageable) {
		return ResponseEntity.ok(riskAssessmentTemplateRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RiskAssessmentTemplateResponse> getRiskAssessmentTemplate(@PathVariable int id) {
		RiskAssessmentTemplate template = riskAssessmentTemplateRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentTemplate not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(template));
	}

	@PostMapping
	public ResponseEntity<Void> createRiskAssessmentTemplate(@Valid @RequestBody RiskAssessmentTemplateRequest request) {
		riskAssessmentTemplateRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRiskAssessmentTemplate(@PathVariable int id) {
		RiskAssessmentTemplate template = riskAssessmentTemplateRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentTemplate not found: " + id));
		template.setIsDeleted(true);
		riskAssessmentTemplateRepository.save(template);
		return ResponseEntity.noContent().build();
	}
}
