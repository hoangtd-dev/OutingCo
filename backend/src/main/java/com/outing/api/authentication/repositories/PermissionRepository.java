package com.outing.api.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
