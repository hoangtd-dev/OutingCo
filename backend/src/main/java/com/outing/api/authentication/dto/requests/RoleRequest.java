package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(

		@NotBlank @Size(max = 64) String roleName,

		@Size(max = 255) String description) {
}
