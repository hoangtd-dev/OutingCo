package com.outing.api.user.dto.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
	@NotBlank(message = "Name must contain at least one non-whitespace character.")
	@Size(max = 255)
	private String name;
}