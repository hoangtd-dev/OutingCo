package com.outing.api.venue.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.venue.dto.requests.RiskAssessmentRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentResponse;
import com.outing.api.venue.entities.RiskAssessment;
import com.outing.api.venue.entities.RiskAssessmentTemplate;
import com.outing.api.venue.entities.Venue;

@Component
public class RiskAssessmentMapper {

	public RiskAssessmentResponse toResponse(RiskAssessment riskAssessment) {
		return new RiskAssessmentResponse(
				riskAssessment.getId(),
				riskAssessment.getVenue().getId(),
				riskAssessment.getTemplate().getId(),
				riskAssessment.getOutingcoRefId(),
				riskAssessment.getRiskScore(),
				riskAssessment.getExpertAnalysis(),
				riskAssessment.getPublishedAt(),
				riskAssessment.getValidUntil(),
				riskAssessment.getIsActive());
	}

	public RiskAssessment toEntity(RiskAssessmentRequest request) {
		RiskAssessment riskAssessment = new RiskAssessment();
		Venue venue = new Venue();
		venue.setId(request.venueId());
		riskAssessment.setVenue(venue);
		RiskAssessmentTemplate template = new RiskAssessmentTemplate();
		template.setId(request.templateId());
		riskAssessment.setTemplate(template);
		riskAssessment.setOutingcoRefId(request.outingcoRefId());
		riskAssessment.setRiskScore(request.riskScore());
		riskAssessment.setExpertAnalysis(request.expertAnalysis());
		riskAssessment.setPublishedAt(request.publishedAt());
		riskAssessment.setValidUntil(request.validUntil());
		riskAssessment.setIsActive(request.isActive());
		return riskAssessment;
	}
}
