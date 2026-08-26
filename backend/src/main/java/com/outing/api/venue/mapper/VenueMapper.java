package com.outing.api.venue.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.outing.api.venue.dto.requests.VenueDeletedRequest;
import com.outing.api.venue.dto.requests.VenueRequest;
import com.outing.api.venue.dto.requests.VenueUpdateRequest;
import com.outing.api.venue.dto.responses.VenueResponse;
import com.outing.api.venue.entities.Venue;

@Mapper(componentModel = "spring")
public interface VenueMapper {
	VenueResponse toResponse(Venue venue);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	Venue toEntityCreated(VenueRequest request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	void updateEntity(VenueUpdateRequest request, @MappingTarget Venue venue);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "name", ignore = true)
	void updateEntity(VenueDeletedRequest request, @MappingTarget Venue venue);

	List<VenueResponse> toResponseList(List<Venue> venues);
}
