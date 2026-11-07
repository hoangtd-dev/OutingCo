package com.outing.api.notification.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.notification.dto.requests.EmailGroupRequest;
import com.outing.api.notification.dto.responses.EmailGroupResponse;
import com.outing.api.notification.entities.EmailGroup;

@Component
public class EmailGroupMapper {

	public EmailGroupResponse toResponse(EmailGroup emailGroup) {
		return new EmailGroupResponse(emailGroup.getId(), emailGroup.getName(), emailGroup.getDescription());
	}

	public EmailGroup toEntity(EmailGroupRequest request) {
		EmailGroup emailGroup = new EmailGroup();
		emailGroup.setName(request.name());
		emailGroup.setDescription(request.description());
		return emailGroup;
	}
}
