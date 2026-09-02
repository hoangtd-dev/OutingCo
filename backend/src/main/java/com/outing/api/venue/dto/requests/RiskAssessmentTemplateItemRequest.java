package com.outing.api.venue.dto.requests;

import java.math.BigDecimal;

import com.outing.api.venue.enums.TemplateItemType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RiskAssessmentTemplateItemRequest(

		@NotNull Integer templateId,

		@NotBlank @Size(max = 200) String question,

		@Size(max = 500) String description,

		@NotNull TemplateItemType type,

		String options,

		@NotNull BigDecimal weight,

		@NotNull Boolean isRequired,

		@NotNull Boolean isBooking,

		@NotNull Integer orderIndex,

		@Size(max = 60) String category) {
}
