package com.outing.api.authentication.entities;

import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
public class Permission extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "resource", nullable = false, length = 64)
	private String resource;

	@Column(name = "feature", nullable = false, length = 100)
	private String feature;

	@Column(name = "action", nullable = false, length = 50)
	private String action;

	@Column(name = "description", length = 255)
	private String description;
}
