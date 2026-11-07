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

import com.outing.api.venue.dto.requests.RiskAssessmentRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentResponse;
import com.outing.api.venue.entities.RiskAssessment;
import com.outing.api.venue.mapper.RiskAssessmentMapper;
import com.outing.api.venue.repositories.RiskAssessmentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/risk-assessments")
public class RiskAssessmentController {

	private final RiskAssessmentRepository riskAssessmentRepository;
	private final RiskAssessmentMapper mapper;

	public RiskAssessmentController(RiskAssessmentRepository riskAssessmentRepository, RiskAssessmentMapper mapper) {
		this.riskAssessmentRepository = riskAssessmentRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RiskAssessmentResponse>> getRiskAssessments(Pageable pageable) {
		return ResponseEntity.ok(riskAssessmentRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RiskAssessmentResponse> getRiskAssessment(@PathVariable int id) {
		RiskAssessment riskAssessment = riskAssessmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessment not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(riskAssessment));
	}

	@PostMapping
	public ResponseEntity<Void> createRiskAssessment(@Valid @RequestBody RiskAssessmentRequest request) {
		riskAssessmentRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRiskAssessment(@PathVariable int id) {
		RiskAssessment riskAssessment = riskAssessmentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessment not found: " + id));
		riskAssessment.setIsDeleted(true);
		riskAssessmentRepository.save(riskAssessment);
		return ResponseEntity.noContent().build();
	}
}
