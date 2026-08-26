package com.outing.api.venue.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VenueRequest {
	@NotBlank
	@Size(min = 2, max = 255)
	private String name;
}
