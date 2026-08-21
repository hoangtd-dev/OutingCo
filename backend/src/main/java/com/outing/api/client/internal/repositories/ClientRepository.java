package com.outing.api.client.internal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.client.internal.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
