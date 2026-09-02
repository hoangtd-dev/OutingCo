package com.outing.api.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {

	@Column(name = "address_number", length = 10)
	private String addressNumber;

	@Column(name = "address_line", length = 100)
	private String addressLine;

	@Column(name = "city", length = 85)
	private String city;

	@Column(name = "state", length = 100)
	private String state;

	@Column(name = "postcode", length = 20)
	private String postcode;

	@Column(name = "country", length = 2, columnDefinition = "CHAR(2)")
	private String country;
}
