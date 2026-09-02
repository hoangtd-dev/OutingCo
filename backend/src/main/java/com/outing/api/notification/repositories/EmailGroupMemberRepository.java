package com.outing.api.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.notification.entities.EmailGroupMember;
import com.outing.api.notification.entities.compositeKey.EmailGroupMemberId;

public interface EmailGroupMemberRepository extends JpaRepository<EmailGroupMember, EmailGroupMemberId> {

}
