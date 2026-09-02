package com.outing.api.client.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outing.api.client.entities.ServicePrice;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, Integer> {

}
