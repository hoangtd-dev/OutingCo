package com.outing.api.venue.entities;

import java.time.LocalDateTime;

import com.outing.api.shared.entities.Address;
import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venues")
@Getter
@Setter
@NoArgsConstructor
public class Venue extends SoftDeleteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false, length = 60)
	private String name;

	@Column(name = "global_venue_id")
	private int globalVenueId;

	@Column(name = "global_version")
	private Integer globalVersion;

	@Column(name = "is_overridden", nullable = false)
	private Boolean isOverridden = false;

	@Embedded
	private Address address;

	@Column(name = "synced_at")
	private LocalDateTime syncedAt;
}