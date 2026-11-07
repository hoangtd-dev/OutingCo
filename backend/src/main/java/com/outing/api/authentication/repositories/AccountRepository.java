package com.outing.api.authentication.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.authentication.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByEmail(String email);
}
