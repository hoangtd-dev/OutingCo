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

import com.outing.api.venue.dto.requests.RiskAssessmentAnswerRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentAnswerResponse;
import com.outing.api.venue.entities.RiskAssessmentAnswer;
import com.outing.api.venue.mapper.RiskAssessmentAnswerMapper;
import com.outing.api.venue.repositories.RiskAssessmentAnswerRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/risk-assessment-answers")
public class RiskAssessmentAnswerController {

	private final RiskAssessmentAnswerRepository riskAssessmentAnswerRepository;
	private final RiskAssessmentAnswerMapper mapper;

	public RiskAssessmentAnswerController(RiskAssessmentAnswerRepository riskAssessmentAnswerRepository,
			RiskAssessmentAnswerMapper mapper) {
		this.riskAssessmentAnswerRepository = riskAssessmentAnswerRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RiskAssessmentAnswerResponse>> getRiskAssessmentAnswers(Pageable pageable) {
		return ResponseEntity.ok(riskAssessmentAnswerRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RiskAssessmentAnswerResponse> getRiskAssessmentAnswer(@PathVariable int id) {
		RiskAssessmentAnswer answer = riskAssessmentAnswerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentAnswer not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(answer));
	}

	@PostMapping
	public ResponseEntity<Void> createRiskAssessmentAnswer(@Valid @RequestBody RiskAssessmentAnswerRequest request) {
		riskAssessmentAnswerRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRiskAssessmentAnswer(@PathVariable int id) {
		RiskAssessmentAnswer answer = riskAssessmentAnswerRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentAnswer not found: " + id));
		answer.setIsDeleted(true);
		riskAssessmentAnswerRepository.save(answer);
		return ResponseEntity.noContent().build();
	}
}
