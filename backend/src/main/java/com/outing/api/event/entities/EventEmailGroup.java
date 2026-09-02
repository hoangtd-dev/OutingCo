package com.outing.api.event.entities;

import com.outing.api.event.entities.compositeKey.EventEmailGroupId;
import com.outing.api.notification.entities.EmailGroup;
import com.outing.api.shared.entities.SoftDeleteEntity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_email_groups")
@Getter
@Setter
@NoArgsConstructor
public class EventEmailGroup extends SoftDeleteEntity {

	@EmbeddedId
	private EventEmailGroupId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("eventEmailId")
	@JoinColumn(name = "event_email_id")
	private EventEmail eventEmail;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("emailGroupId")
	@JoinColumn(name = "email_group_id")
	private EmailGroup emailGroup;
}