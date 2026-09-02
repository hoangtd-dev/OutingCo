package com.outing.api.event.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.event.dto.requests.EventEmailGroupRequest;
import com.outing.api.event.dto.responses.EventEmailGroupResponse;
import com.outing.api.event.entities.EventEmail;
import com.outing.api.event.entities.EventEmailGroup;
import com.outing.api.event.entities.compositeKey.EventEmailGroupId;
import com.outing.api.notification.entities.EmailGroup;

@Component
public class EventEmailGroupMapper {

	public EventEmailGroupResponse toResponse(EventEmailGroup eventEmailGroup) {
		return new EventEmailGroupResponse(
				eventEmailGroup.getEventEmail().getId(),
				eventEmailGroup.getEmailGroup().getId());
	}

	public EventEmailGroup toEntity(EventEmailGroupRequest request) {
		EventEmailGroup eventEmailGroup = new EventEmailGroup();
		eventEmailGroup.setId(new EventEmailGroupId(request.eventEmailId(), request.emailGroupId()));
		EventEmail eventEmail = new EventEmail();
		eventEmail.setId(request.eventEmailId());
		eventEmailGroup.setEventEmail(eventEmail);
		EmailGroup emailGroup = new EmailGroup();
		emailGroup.setId(request.emailGroupId());
		eventEmailGroup.setEmailGroup(emailGroup);
		return eventEmailGroup;
	}
}
