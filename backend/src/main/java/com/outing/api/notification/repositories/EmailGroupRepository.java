package com.outing.api.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.notification.entities.EmailGroup;

public interface EmailGroupRepository extends JpaRepository<EmailGroup, Integer> {

}
