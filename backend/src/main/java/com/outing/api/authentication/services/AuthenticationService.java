package com.outing.api.authentication.services;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.outing.api.authentication.dto.requests.LoginRequest;
import com.outing.api.authentication.dto.requests.RegisterRequest;
import com.outing.api.authentication.dto.responses.LoginResponse;
import com.outing.api.authentication.entities.Account;
import com.outing.api.authentication.entities.User;
import com.outing.api.authentication.enums.LoginMethod;
import com.outing.api.authentication.repositories.AccountRepository;
import com.outing.api.authentication.repositories.UserRepository;
import com.outing.api.authentication.security.JwtService;

@Service
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;

	private final AccountRepository accountRepository;

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	public AuthenticationService(AuthenticationManager authenticationManager, AccountRepository accountRepository,
			UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public LoginResponse login(LoginRequest request) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.email(), request.password()));
		} catch (AuthenticationException exception) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
		}

		List<String> roles = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.filter(authority -> authority.startsWith("ROLE_"))
				.map(authority -> authority.substring("ROLE_".length()))
				.toList();

		String token = jwtService.generateToken(authentication.getName(), roles);

		return new LoginResponse(token, jwtService.expiresAt(Instant.now()), authentication.getName(), roles);
	}

	@Transactional
	public void register(RegisterRequest request) {
		if (accountRepository.findByEmail(request.email()).isPresent()
				|| userRepository.existsByEmail(request.email())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
		}

		User user = new User();
		user.setFirstName(request.firstName());
		user.setLastName(request.lastName());
		user.setEmail(request.email());
		user.setIsActive(true);
		userRepository.save(user);

		Account account = new Account();
		account.setUser(user);
		account.setEmail(request.email());
		account.setPassword(passwordEncoder.encode(request.password()));
		account.setLoginMethod(LoginMethod.LOCAL);
		accountRepository.save(account);
	}
}
