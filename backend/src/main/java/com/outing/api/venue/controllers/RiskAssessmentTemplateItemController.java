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

import com.outing.api.venue.dto.requests.RiskAssessmentTemplateItemRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentTemplateItemResponse;
import com.outing.api.venue.entities.RiskAssessmentTemplateItem;
import com.outing.api.venue.mapper.RiskAssessmentTemplateItemMapper;
import com.outing.api.venue.repositories.RiskAssessmentTemplateItemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/risk-assessment-template-items")
public class RiskAssessmentTemplateItemController {

	private final RiskAssessmentTemplateItemRepository riskAssessmentTemplateItemRepository;
	private final RiskAssessmentTemplateItemMapper mapper;

	public RiskAssessmentTemplateItemController(RiskAssessmentTemplateItemRepository riskAssessmentTemplateItemRepository,
			RiskAssessmentTemplateItemMapper mapper) {
		this.riskAssessmentTemplateItemRepository = riskAssessmentTemplateItemRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<RiskAssessmentTemplateItemResponse>> getRiskAssessmentTemplateItems(Pageable pageable) {
		return ResponseEntity.ok(riskAssessmentTemplateItemRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RiskAssessmentTemplateItemResponse> getRiskAssessmentTemplateItem(@PathVariable int id) {
		RiskAssessmentTemplateItem item = riskAssessmentTemplateItemRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentTemplateItem not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(item));
	}

	@PostMapping
	public ResponseEntity<Void> createRiskAssessmentTemplateItem(@Valid @RequestBody RiskAssessmentTemplateItemRequest request) {
		riskAssessmentTemplateItemRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRiskAssessmentTemplateItem(@PathVariable int id) {
		RiskAssessmentTemplateItem item = riskAssessmentTemplateItemRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RiskAssessmentTemplateItem not found: " + id));
		item.setIsDeleted(true);
		riskAssessmentTemplateItemRepository.save(item);
		return ResponseEntity.noContent().build();
	}
}
