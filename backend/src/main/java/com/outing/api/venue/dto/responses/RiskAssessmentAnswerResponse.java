package com.outing.api.venue.dto.responses;

public record RiskAssessmentAnswerResponse(
		int id,
		int assessmentId,
		int templateItemId,
		String answerValue) {
}
