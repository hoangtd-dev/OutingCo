package com.outing.api.notification.dto.requests;

import jakarta.validation.constraints.NotNull;

public record EmailGroupMemberRequest(

		@NotNull int groupId,

		@NotNull int userId) {
}
