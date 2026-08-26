package com.outing.api.venue.dto.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VenueDeletedRequest {
	private boolean deleted;
}
