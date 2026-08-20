package com.outing.api.client.internal.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import com.outing.api.client.internal.entities.Client;

@DataJpaTest
class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private TestEntityManager entityManager;

	private Client newClient() {
		return Client.builder()
				.firstName("Alice")
				.lastName("Nguyen")
				.dateOfBirth(LocalDate.of(1990, 5, 17))
				.address("12 Harbour St, Sydney NSW")
				.phoneNumber("0412345678")
				.build();
	}

	@Test
	void saveAndFindById_roundTripsAllFields() {
		Client saved = clientRepository.save(newClient());
		entityManager.flush();
		entityManager.clear();

		Client found = clientRepository.findById(saved.getId()).orElseThrow();

		assertThat(found.getId()).isNotNull();
		assertThat(found.getFirstName()).isEqualTo("Alice");
		assertThat(found.getLastName()).isEqualTo("Nguyen");
		assertThat(found.getDateOfBirth()).isEqualTo(LocalDate.of(1990, 5, 17));
		assertThat(found.getAddress()).isEqualTo("12 Harbour St, Sydney NSW");
		assertThat(found.getPhoneNumber()).isEqualTo("0412345678");
	}

	@Test
	void update_persistsChangedFields() {
		Client saved = clientRepository.save(newClient());
		entityManager.flush();

		saved.setPhoneNumber("0499999999");
		clientRepository.save(saved);
		entityManager.flush();
		entityManager.clear();

		Client reloaded = clientRepository.findById(saved.getId()).orElseThrow();

		assertThat(reloaded.getPhoneNumber()).isEqualTo("0499999999");
		assertThat(reloaded.getFirstName()).isEqualTo("Alice");
	}

	@Test
	void deleteById_removesTheRow() {
		Client saved = clientRepository.save(newClient());
		entityManager.flush();
		UUID id = saved.getId();

		clientRepository.deleteById(id);
		entityManager.flush();

		assertThat(clientRepository.findById(id)).isEmpty();
	}
}
