package com.outing.api.venue.dto.responses;

import java.math.BigDecimal;

import com.outing.api.venue.enums.TemplateItemType;

public record RiskAssessmentTemplateItemResponse(
		int id,
		int templateId,
		String question,
		String description,
		TemplateItemType type,
		String options,
		BigDecimal weight,
		Boolean isRequired,
		Boolean isBooking,
		Integer orderIndex,
		String category) {
}
