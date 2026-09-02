package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.NotNull;

public record UserRoleRequest(

		@NotNull int userId,

		@NotNull int roleId) {
}
