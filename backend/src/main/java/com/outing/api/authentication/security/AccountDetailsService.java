package com.outing.api.authentication.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.outing.api.authentication.entities.Account;
import com.outing.api.authentication.repositories.AccountRepository;
import com.outing.api.authentication.repositories.UserRoleRepository;

@Service
public class AccountDetailsService implements UserDetailsService {

	private final AccountRepository accountRepository;

	private final UserRoleRepository userRoleRepository;

	public AccountDetailsService(AccountRepository accountRepository, UserRoleRepository userRoleRepository) {
		this.accountRepository = accountRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Account not found: " + email));

		if (account.getPassword() == null) {
			throw new UsernameNotFoundException("Account has no local password: " + email);
		}

		return org.springframework.security.core.userdetails.User
				.withUsername(account.getEmail())
				.password(account.getPassword())
				.authorities(findRoleNames(account.getUser().getId()).stream()
						.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
						.toList())
				.disabled(!Boolean.TRUE.equals(account.getUser().getIsActive()))
				.build();
	}

	public List<String> findRoleNames(int userId) {
		return userRoleRepository.findByUserId(userId).stream()
				.map(userRole -> userRole.getRole().getRoleName())
				.toList();
	}
}
