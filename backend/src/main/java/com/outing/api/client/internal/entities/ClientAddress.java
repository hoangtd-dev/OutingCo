package com.outing.api.client.internal.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClientAddress {

	@Column(name = "street", length = 255)
	private String street;

	@Column(name = "suburb", length = 100)
	private String suburb;

	@Column(name = "state", length = 3)
	private String state;

	@Column(name = "postcode", length = 4)
	private String postcode;

	public ClientAddress(String street, String suburb, String state, String postcode) {
		this.street = street;
		this.suburb = suburb;
		this.state = state;
		this.postcode = postcode;
	}
}
