package com.outing.api.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.client.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
