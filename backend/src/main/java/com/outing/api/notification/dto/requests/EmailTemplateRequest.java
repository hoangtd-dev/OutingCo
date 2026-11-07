package com.outing.api.notification.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmailTemplateRequest(

		@NotBlank @Size(max = 100) String name,

		String layout) {
}
