package com.outing.api.event.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(

		@NotNull int bookingId,

		Integer engagementLevel,

		@Size(max = 500) String observationNote) {
}
