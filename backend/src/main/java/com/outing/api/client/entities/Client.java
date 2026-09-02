package com.outing.api.client.entities;

import java.time.LocalDate;

import com.outing.api.authentication.entities.User;
import com.outing.api.shared.entities.Address;
import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "case_manager_id", nullable = false)
	private User caseManager;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;

	@Column(name = "email", nullable = false, length = 254)
	private String email;

	@Embedded
	private Address address;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "client_number", length = 9)
	private String clientNumber;

	@Column(name = "phone", length = 15)
	private String phone;

	@Column(name = "emergency_contact_name", length = 150)
	private String emergencyContactName;

	@Column(name = "emergency_contact_relationship", length = 50)
	private String emergencyContactRelationship;

	@Column(name = "emergency_contact_phone_primary", length = 20)
	private String emergencyContactPhonePrimary;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;
}
