package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.entities.User;
import com.outing.api.event.dto.requests.EventEmailRequest;
import com.outing.api.event.dto.responses.EventEmailResponse;
import com.outing.api.event.entities.EventEmail;
import com.outing.api.event.entities.EventSession;
import com.outing.api.notification.entities.EmailTemplate;

@Component
public class EventEmailMapper {

	public EventEmailResponse toResponse(EventEmail eventEmail) {
		return new EventEmailResponse(
				eventEmail.getId(),
				eventEmail.getEventSession().getId(),
				eventEmail.getEmailTemplate() != null ? eventEmail.getEmailTemplate().getId() : null,
				eventEmail.getSubject(),
				eventEmail.getBody(),
				eventEmail.getStatus(),
				eventEmail.getApprovedBy() != null ? eventEmail.getApprovedBy().getId() : null,
				eventEmail.getApprovedAt(),
				eventEmail.getSentAt());
	}

	public EventEmail toEntity(EventEmailRequest request) {
		EventEmail eventEmail = new EventEmail();
		EventSession eventSession = new EventSession();
		eventSession.setId(request.eventSessionId());
		eventEmail.setEventSession(eventSession);
		if (request.emailTemplateId() != null) {
			EmailTemplate emailTemplate = new EmailTemplate();
			emailTemplate.setId(request.emailTemplateId());
			eventEmail.setEmailTemplate(emailTemplate);
		}
		eventEmail.setSubject(request.subject());
		eventEmail.setBody(request.body());
		if (request.status() != null) {
			eventEmail.setStatus(request.status());
		}
		if (request.approvedById() != null) {
			User approvedBy = new User();
			approvedBy.setId(request.approvedById());
			eventEmail.setApprovedBy(approvedBy);
		}
		eventEmail.setApprovedAt(request.approvedAt());
		eventEmail.setSentAt(request.sentAt());
		return eventEmail;
	}
}
