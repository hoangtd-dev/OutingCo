package com.outing.api.venue.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RiskAssessmentTemplateRequest(

		@NotBlank @Size(max = 100) String name,

		String description,

		@NotNull Boolean isGlobal,

		Integer parentTemplateId,

		@NotNull Integer version,

		@NotNull Boolean isActive) {
}
