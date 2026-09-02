package com.outing.api.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
