package com.outing.api.shared.entities;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@NoArgsConstructor
public abstract class SoftDeleteEntity extends BaseEntity {
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
}