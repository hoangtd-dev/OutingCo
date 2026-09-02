package com.outing.api.venue.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.venue.dto.requests.RiskAssessmentAnswerRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentAnswerResponse;
import com.outing.api.venue.entities.RiskAssessment;
import com.outing.api.venue.entities.RiskAssessmentAnswer;
import com.outing.api.venue.entities.RiskAssessmentTemplateItem;

@Component
public class RiskAssessmentAnswerMapper {

	public RiskAssessmentAnswerResponse toResponse(RiskAssessmentAnswer answer) {
		return new RiskAssessmentAnswerResponse(
				answer.getId(),
				answer.getAssessment().getId(),
				answer.getTemplateItem().getId(),
				answer.getAnswerValue());
	}

	public RiskAssessmentAnswer toEntity(RiskAssessmentAnswerRequest request) {
		RiskAssessmentAnswer answer = new RiskAssessmentAnswer();
		RiskAssessment assessment = new RiskAssessment();
		assessment.setId(request.assessmentId());
		answer.setAssessment(assessment);
		RiskAssessmentTemplateItem templateItem = new RiskAssessmentTemplateItem();
		templateItem.setId(request.templateItemId());
		answer.setTemplateItem(templateItem);
		answer.setAnswerValue(request.answerValue());
		return answer;
	}
}
