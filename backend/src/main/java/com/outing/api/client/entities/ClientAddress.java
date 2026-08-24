package com.outing.api.client.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ClientAddress {

	@Column(name = "street", length = 255)
	private String street;

	@Column(name = "suburb", length = 100)
	private String suburb;

	@Column(name = "state", length = 3)
	private String state;

	@Column(name = "postcode", length = 4)
	private String postcode;
}
