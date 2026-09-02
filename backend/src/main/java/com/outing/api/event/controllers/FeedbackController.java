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

import com.outing.api.event.dto.requests.FeedbackRequest;
import com.outing.api.event.dto.responses.FeedbackResponse;
import com.outing.api.event.entities.Feedback;
import com.outing.api.event.mapper.FeedbackMapper;
import com.outing.api.event.repositories.FeedbackRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

	private final FeedbackRepository feedbackRepository;
	private final FeedbackMapper mapper;

	public FeedbackController(FeedbackRepository feedbackRepository, FeedbackMapper mapper) {
		this.feedbackRepository = feedbackRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<FeedbackResponse>> getFeedbacks(Pageable pageable) {
		return ResponseEntity.ok(feedbackRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<FeedbackResponse> getFeedback(@PathVariable int id) {
		Feedback feedback = feedbackRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(feedback));
	}

	@PostMapping
	public ResponseEntity<Void> createFeedback(@Valid @RequestBody FeedbackRequest request) {
		feedbackRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
		Feedback feedback = feedbackRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found: " + id));
		feedback.setIsDeleted(true);
		feedbackRepository.save(feedback);
		return ResponseEntity.noContent().build();
	}
}
