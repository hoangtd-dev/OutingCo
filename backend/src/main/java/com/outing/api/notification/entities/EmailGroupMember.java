package com.outing.api.notification.entities;

import com.outing.api.authentication.entities.User;
import com.outing.api.notification.entities.compositeKey.EmailGroupMemberId;
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
@Table(name = "email_group_members")
@Getter
@Setter
@NoArgsConstructor
public class EmailGroupMember extends SoftDeleteEntity {

	@EmbeddedId
	private EmailGroupMemberId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("groupId")
	@JoinColumn(name = "group_id")
	private EmailGroup group;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;
}
