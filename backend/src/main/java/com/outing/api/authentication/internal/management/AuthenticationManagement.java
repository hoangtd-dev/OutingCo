package com.outing.api.authentication.internal.management;

import com.outing.api.authentication.api.AuthenticationExternalAPI;
import com.outing.api.authentication.internal.api.AuthenticationInternalAPI;
import com.outing.api.client.api.ClientExternalAPI;

public class AuthenticationManagement implements AuthenticationExternalAPI, AuthenticationInternalAPI {

	public AuthenticationManagement(ClientExternalAPI client) {
	}
}