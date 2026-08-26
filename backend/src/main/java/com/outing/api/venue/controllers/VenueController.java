package com.outing.api.venue.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outing.api.venue.dto.requests.VenueRequest;
import com.outing.api.venue.dto.requests.VenueUpdateRequest;
import com.outing.api.venue.dto.responses.VenueResponse;
import com.outing.api.venue.services.VenueService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
	private final VenueService venueService;

	public VenueController(VenueService venueService) {
		this.venueService = venueService;
	}

	@GetMapping
	public ResponseEntity<List<VenueResponse>> getVenues() {
		return ResponseEntity.ok(this.venueService.getVenues());
	}

	@PostMapping
	public ResponseEntity<Void> createVenue(@Valid @RequestBody VenueRequest request) {
		this.venueService.createVenue(request);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> patchVenue(@PathVariable int id, @Valid @RequestBody VenueUpdateRequest updatedRequest) {
		this.venueService.patchVenue(id, updatedRequest);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVenue(@PathVariable int id) {
		this.venueService.deleteVenue(id);

		return ResponseEntity.noContent().build();
	}
}
