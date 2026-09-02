package com.outing.api.client.dto;

import java.time.LocalDate;

public record ClientResponse(
		int id,
		int caseManagerId,
		String firstName,
		String lastName,
		String email,
		AddressRequest address,
		LocalDate dateOfBirth,
		String clientNumber,
		String phone,
		String emergencyContactName,
		String emergencyContactRelationship,
		String emergencyContactPhonePrimary,
		boolean isActive) {
}
