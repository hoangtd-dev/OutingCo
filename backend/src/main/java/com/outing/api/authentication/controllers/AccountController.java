package com.outing.api.authentication.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.authentication.dto.requests.AccountRequest;
import com.outing.api.authentication.dto.responses.AccountResponse;
import com.outing.api.authentication.entities.Account;
import com.outing.api.authentication.mapper.AccountMapper;
import com.outing.api.authentication.repositories.AccountRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	private final AccountRepository accountRepository;
	private final AccountMapper mapper;

	public AccountController(AccountRepository accountRepository, AccountMapper mapper) {
		this.accountRepository = accountRepository;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<Page<AccountResponse>> getAccounts(Pageable pageable) {
		return ResponseEntity.ok(accountRepository.findAll(pageable).map(mapper::toResponse));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountResponse> getAccount(@PathVariable int id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found: " + id));
		return ResponseEntity.ok(mapper.toResponse(account));
	}

	@PostMapping
	public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountRequest request) {
		accountRepository.save(mapper.toEntity(request));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found: " + id));
		account.setIsDeleted(true);
		accountRepository.save(account);
		return ResponseEntity.noContent().build();
	}
}
