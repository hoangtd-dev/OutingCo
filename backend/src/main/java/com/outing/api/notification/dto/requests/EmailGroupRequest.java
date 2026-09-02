package com.outing.api.notification.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailGroupRequest(

		@NotBlank @Size(max = 50) String name,

		@Size(max = 254) String description) {
}
