package com.outing.api.event.dto.requests;

import jakarta.validation.constraints.NotNull;

public record EventEmailGroupRequest(

		@NotNull int eventEmailId,

		@NotNull int emailGroupId) {
}
