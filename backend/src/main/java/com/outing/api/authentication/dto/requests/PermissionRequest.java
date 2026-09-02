package com.outing.api.authentication.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PermissionRequest(

		@NotBlank @Size(max = 64) String resource,

		@NotBlank @Size(max = 100) String feature,

		@NotBlank @Size(max = 50) String action,

		@Size(max = 255) String description) {
}
