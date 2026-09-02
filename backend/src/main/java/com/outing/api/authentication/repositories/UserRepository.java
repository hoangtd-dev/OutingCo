package com.outing.api.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
