package com.outing.api.venue.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RiskAssessmentAnswerRequest(

		@NotNull Integer assessmentId,

		@NotNull Integer templateItemId,

		@NotBlank String answerValue) {
}
