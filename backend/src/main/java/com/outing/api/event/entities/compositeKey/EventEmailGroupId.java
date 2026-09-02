package com.outing.api.event.entities.compositeKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EventEmailGroupId implements Serializable {

	@Column(name = "event_email_id")
	private int eventEmailId;

	@Column(name = "email_group_id")
	private int emailGroupId;
}