package com.outing.api.venue.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.outing.api.venue.dto.requests.VenueDeletedRequest;
import com.outing.api.venue.dto.requests.VenueRequest;
import com.outing.api.venue.dto.requests.VenueUpdateRequest;
import com.outing.api.venue.dto.responses.VenueResponse;
import com.outing.api.venue.entities.Venue;

@Component
public class VenueMapper {

	public VenueResponse toResponse(Venue venue) {
		return new VenueResponse(venue.getId(), venue.getName());
	}

	public List<VenueResponse> toResponseList(List<Venue> venues) {
		return venues.stream().map(this::toResponse).toList();
	}

	public Venue toEntityCreated(VenueRequest request) {
		Venue venue = new Venue();
		venue.setName(request.getName());
		return venue;
	}

	public void updateEntity(VenueUpdateRequest request, Venue venue) {
		venue.setName(request.getName());
	}

	public void updateEntity(VenueDeletedRequest request, Venue venue) {
		venue.setIsDeleted(request.isDeleted());
	}
}
