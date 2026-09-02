package com.outing.api.venue.dto.responses;

public record RiskAssessmentTemplateResponse(
		int id,
		String name,
		String description,
		Boolean isGlobal,
		Integer parentTemplateId,
		Integer version,
		Boolean isActive) {
}
