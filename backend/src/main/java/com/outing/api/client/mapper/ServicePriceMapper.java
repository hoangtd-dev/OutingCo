package com.outing.api.client.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.client.dto.requests.ServicePriceRequest;
import com.outing.api.client.dto.responses.ServicePriceResponse;
import com.outing.api.client.entities.ServicePrice;

@Component
public class ServicePriceMapper {

	public ServicePriceResponse toResponse(ServicePrice servicePrice) {
		return new ServicePriceResponse(
				servicePrice.getId(),
				servicePrice.getName(),
				servicePrice.getPrice(),
				servicePrice.getIsActive());
	}

	public ServicePrice toEntity(ServicePriceRequest request) {
		ServicePrice servicePrice = new ServicePrice();
		servicePrice.setName(request.name());
		servicePrice.setPrice(request.price());
		if (request.isActive() != null) {
			servicePrice.setIsActive(request.isActive());
		}
		return servicePrice;
	}
}
