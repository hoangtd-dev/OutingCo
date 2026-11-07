package com.outing.api.venue.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.venue.dto.requests.RiskAssessmentTemplateRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentTemplateResponse;
import com.outing.api.venue.entities.RiskAssessmentTemplate;

@Component
public class RiskAssessmentTemplateMapper {

	public RiskAssessmentTemplateResponse toResponse(RiskAssessmentTemplate template) {
		return new RiskAssessmentTemplateResponse(
				template.getId(),
				template.getName(),
				template.getDescription(),
				template.getIsGlobal(),
				template.getParentTemplate() != null ? template.getParentTemplate().getId() : null,
				template.getVersion(),
				template.getIsActive());
	}

	public RiskAssessmentTemplate toEntity(RiskAssessmentTemplateRequest request) {
		RiskAssessmentTemplate template = new RiskAssessmentTemplate();
		template.setName(request.name());
		template.setDescription(request.description());
		template.setIsGlobal(request.isGlobal());
		if (request.parentTemplateId() != null) {
			RiskAssessmentTemplate parentTemplate = new RiskAssessmentTemplate();
			parentTemplate.setId(request.parentTemplateId());
			template.setParentTemplate(parentTemplate);
		}
		template.setVersion(request.version());
		template.setIsActive(request.isActive());
		return template;
	}
}
