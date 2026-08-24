package com.outing.api.venue.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.outing.api.venue.dto.requests.VenueRequest;
import com.outing.api.venue.dto.requests.VenueUpdateRequest;
import com.outing.api.venue.dto.responses.VenueResponse;
import com.outing.api.venue.mapper.VenueMapper;
import com.outing.api.venue.repositories.VenueRepository;

import jakarta.transaction.Transactional;

@Service
public class VenueService {
	private final VenueRepository venueRepository;
	private final VenueMapper mapper;

	public VenueService(VenueRepository venueRepository, VenueMapper mapper) {
		this.venueRepository = venueRepository;
		this.mapper = mapper;
	}

	public List<VenueResponse> getVenues() {
		var venues = venueRepository.findAll();

		return mapper.toResponseList(venues);
	}

	@Transactional
	public void createVenue(VenueRequest request) {
		var venueEntity = mapper.toEntityCreated(request);

		venueRepository.save(venueEntity);
	}

	@Transactional
	public void patchVenue(int id, VenueUpdateRequest request) {
		var venueEntity = venueRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found: " + id));

		mapper.updateEntity(request, venueEntity);

		venueRepository.save(venueEntity);
	}

	@Transactional
	public void deleteVenue(int id) {
		var venueEntity = venueRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found: " + id));

		venueEntity.setDeleted(true);
		venueRepository.save(venueEntity);
	}
}
