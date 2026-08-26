package com.outing.api.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.user.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
