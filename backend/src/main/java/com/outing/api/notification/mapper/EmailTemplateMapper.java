package com.outing.api.notification.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.notification.dto.requests.EmailTemplateRequest;
import com.outing.api.notification.dto.responses.EmailTemplateResponse;
import com.outing.api.notification.entities.EmailTemplate;

@Component
public class EmailTemplateMapper {

	public EmailTemplateResponse toResponse(EmailTemplate emailTemplate) {
		return new EmailTemplateResponse(emailTemplate.getId(), emailTemplate.getName(), emailTemplate.getLayout());
	}

	public EmailTemplate toEntity(EmailTemplateRequest request) {
		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setName(request.name());
		emailTemplate.setLayout(request.layout());
		return emailTemplate;
	}
}
