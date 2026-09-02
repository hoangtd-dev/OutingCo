package com.outing.api.notification.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.entities.User;
import com.outing.api.notification.dto.requests.EmailGroupMemberRequest;
import com.outing.api.notification.dto.responses.EmailGroupMemberResponse;
import com.outing.api.notification.entities.EmailGroup;
import com.outing.api.notification.entities.EmailGroupMember;
import com.outing.api.notification.entities.compositeKey.EmailGroupMemberId;

@Component
public class EmailGroupMemberMapper {

	public EmailGroupMemberResponse toResponse(EmailGroupMember emailGroupMember) {
		return new EmailGroupMemberResponse(
				emailGroupMember.getGroup().getId(),
				emailGroupMember.getUser().getId());
	}

	public EmailGroupMember toEntity(EmailGroupMemberRequest request) {
		EmailGroupMember emailGroupMember = new EmailGroupMember();
		emailGroupMember.setId(new EmailGroupMemberId(request.groupId(), request.userId()));
		EmailGroup group = new EmailGroup();
		group.setId(request.groupId());
		emailGroupMember.setGroup(group);
		User user = new User();
		user.setId(request.userId());
		emailGroupMember.setUser(user);
		return emailGroupMember;
	}
}
