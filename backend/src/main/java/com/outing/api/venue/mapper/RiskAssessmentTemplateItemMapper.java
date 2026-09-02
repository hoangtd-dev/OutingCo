package com.outing.api.venue.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.venue.dto.requests.RiskAssessmentTemplateItemRequest;
import com.outing.api.venue.dto.responses.RiskAssessmentTemplateItemResponse;
import com.outing.api.venue.entities.RiskAssessmentTemplate;
import com.outing.api.venue.entities.RiskAssessmentTemplateItem;

@Component
public class RiskAssessmentTemplateItemMapper {

	public RiskAssessmentTemplateItemResponse toResponse(RiskAssessmentTemplateItem item) {
		return new RiskAssessmentTemplateItemResponse(
				item.getId(),
				item.getTemplate().getId(),
				item.getQuestion(),
				item.getDescription(),
				item.getType(),
				item.getOptions(),
				item.getWeight(),
				item.getIsRequired(),
				item.getIsBooking(),
				item.getOrderIndex(),
				item.getCategory());
	}

	public RiskAssessmentTemplateItem toEntity(RiskAssessmentTemplateItemRequest request) {
		RiskAssessmentTemplateItem item = new RiskAssessmentTemplateItem();
		RiskAssessmentTemplate template = new RiskAssessmentTemplate();
		template.setId(request.templateId());
		item.setTemplate(template);
		item.setQuestion(request.question());
		item.setDescription(request.description());
		item.setType(request.type());
		item.setOptions(request.options());
		item.setWeight(request.weight());
		item.setIsRequired(request.isRequired());
		item.setIsBooking(request.isBooking());
		item.setOrderIndex(request.orderIndex());
		item.setCategory(request.category());
		return item;
	}
}
