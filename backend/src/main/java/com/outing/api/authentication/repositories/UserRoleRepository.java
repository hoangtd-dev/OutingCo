package com.outing.api.authentication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.UserRole;
import com.outing.api.authentication.entities.compositeKey.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

	List<UserRole> findByUserId(int userId);
}
