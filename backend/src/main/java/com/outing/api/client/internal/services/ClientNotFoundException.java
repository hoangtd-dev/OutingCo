package com.outing.api.client.internal.services;

public class ClientNotFoundException extends RuntimeException {

	public ClientNotFoundException(int id) {
		super("Client not found: " + id);
	}
}
