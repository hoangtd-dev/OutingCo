package com.outing.api.venue.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.venue.dto.requests.VenueRequest;
import com.outing.api.venue.dto.requests.VenueUpdateRequest;
import com.outing.api.venue.dto.responses.VenueResponse;
import com.outing.api.venue.entities.Venue;
import com.outing.api.venue.mapper.VenueMapper;
import com.outing.api.venue.repositories.VenueRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

	private final VenueRepository venueRepository;

	private final VenueMapper mapper;

	public VenueController(VenueRepository venueRepository, VenueMapper mapper) {
		this.venueRepository = venueRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<VenueResponse>> getVenues() {
		List<Venue> venues = venueRepository.findAll();
		return ResponseEntity.ok(mapper.toResponseList(venues));
	}

	@PostMapping
	public ResponseEntity<Void> createVenue(@Valid @RequestBody VenueRequest request) {
		Venue venueEntity = mapper.toEntityCreated(request);
		venueRepository.save(venueEntity);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Void> patchVenue(@PathVariable int id, @Valid @RequestBody VenueUpdateRequest updatedRequest) {
		Venue venueEntity = requireVenue(id);
		mapper.updateEntity(updatedRequest, venueEntity);
		venueRepository.save(venueEntity);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVenue(@PathVariable int id) {
		Venue venueEntity = requireVenue(id);
		venueEntity.setDeleted(true);
		venueRepository.save(venueEntity);
		return ResponseEntity.noContent().build();
	}

	private Venue requireVenue(int id) {
		return venueRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found: " + id));
	}
}
