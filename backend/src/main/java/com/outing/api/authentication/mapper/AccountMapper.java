package com.outing.api.authentication.mapper;

import org.springframework.stereotype.Component;

import com.outing.api.authentication.dto.requests.AccountRequest;
import com.outing.api.authentication.dto.responses.AccountResponse;
import com.outing.api.authentication.entities.Account;
import com.outing.api.authentication.entities.User;

@Component
public class AccountMapper {

	public AccountResponse toResponse(Account account) {
		return new AccountResponse(
				account.getId(),
				account.getUser().getId(),
				account.getEmail(),
				account.getLoginMethod(),
				account.getExternalAuthenticationId());
	}

	public Account toEntity(AccountRequest request) {
		Account account = new Account();
		User user = new User();
		user.setId(request.userId());
		account.setUser(user);
		account.setEmail(request.email());
		account.setPassword(request.password());
		account.setLoginMethod(request.loginMethod());
		account.setExternalAuthenticationId(request.externalAuthenticationId());
		return account;
	}
}
