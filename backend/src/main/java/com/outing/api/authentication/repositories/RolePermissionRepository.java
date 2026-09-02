package com.outing.api.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.RolePermission;
import com.outing.api.authentication.entities.compositeKey.RolePermissionId;

public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId> {

}
