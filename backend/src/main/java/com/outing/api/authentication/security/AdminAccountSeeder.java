package com.outing.api.authentication.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.outing.api.authentication.entities.Account;
import com.outing.api.authentication.entities.Role;
import com.outing.api.authentication.entities.User;
import com.outing.api.authentication.entities.UserRole;
import com.outing.api.authentication.entities.compositeKey.UserRoleId;
import com.outing.api.authentication.enums.LoginMethod;
import com.outing.api.authentication.repositories.AccountRepository;
import com.outing.api.authentication.repositories.RoleRepository;
import com.outing.api.authentication.repositories.UserRepository;
import com.outing.api.authentication.repositories.UserRoleRepository;

@Component
public class AdminAccountSeeder implements CommandLineRunner {

	private static final String ADMIN_ROLE = "ADMIN";

	private static final Logger log = LoggerFactory.getLogger(AdminAccountSeeder.class);

	private final AccountRepository accountRepository;

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserRoleRepository userRoleRepository;

	private final PasswordEncoder passwordEncoder;

	private final String adminEmail;

	private final String adminPassword;

	public AdminAccountSeeder(AccountRepository accountRepository, UserRepository userRepository,
			RoleRepository roleRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,
			@Value("${app.seed.admin-email:}") String adminEmail,
			@Value("${app.seed.admin-password:}") String adminPassword) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}

	@Override
	@Transactional
	public void run(String... args) {
		if (adminEmail.isBlank() || adminPassword.isBlank()) {
			log.info("Admin seeding skipped: app.seed.admin-email and app.seed.admin-password are not both set");
			return;
		}

		if (accountRepository.findByEmail(adminEmail).isPresent()) {
			log.info("Admin seeding skipped: an account already exists for {}", adminEmail);
			return;
		}

		Role adminRole = roleRepository.findByRoleName(ADMIN_ROLE).orElseGet(this::createAdminRole);

		User adminUser = new User();
		adminUser.setFirstName("System");
		adminUser.setLastName("Administrator");
		adminUser.setEmail(adminEmail);
		adminUser.setIsActive(true);
		userRepository.save(adminUser);

		UserRole userRole = new UserRole();
		userRole.setId(new UserRoleId(adminUser.getId(), adminRole.getId()));
		userRole.setUser(adminUser);
		userRole.setRole(adminRole);
		userRoleRepository.save(userRole);

		Account account = new Account();
		account.setUser(adminUser);
		account.setEmail(adminEmail);
		account.setPassword(passwordEncoder.encode(adminPassword));
		account.setLoginMethod(LoginMethod.LOCAL);
		accountRepository.save(account);

		log.warn("Seeded admin account {} - change this password before any shared deployment", adminEmail);
	}

	private Role createAdminRole() {
		Role role = new Role();
		role.setRoleName(ADMIN_ROLE);
		role.setDescription("Full access to every resource");
		return roleRepository.save(role);
	}
}
