package com.outing.api.client.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	List<Client> findAllByDeletedFalse();

	Optional<Client> findByIdAndDeletedFalse(int id);
}
