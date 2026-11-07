package com.outing.api.notification.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.notification.entities.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {

}
