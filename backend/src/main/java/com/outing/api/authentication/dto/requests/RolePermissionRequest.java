package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.NotNull;

public record RolePermissionRequest(

		@NotNull int roleId,

		@NotNull int permissionId) {
}
