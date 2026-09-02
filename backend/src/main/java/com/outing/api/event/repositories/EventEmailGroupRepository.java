package com.outing.api.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.event.entities.EventEmailGroup;
import com.outing.api.event.entities.compositeKey.EventEmailGroupId;

public interface EventEmailGroupRepository extends JpaRepository<EventEmailGroup, EventEmailGroupId> {

}
