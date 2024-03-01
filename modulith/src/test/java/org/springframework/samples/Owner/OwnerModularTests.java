package org.springframework.samples.Owner;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Owner.repository.OwnerRepository;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class OwnerModularTests {

	@Autowired
	protected OwnerRepository owners;

	Pageable pageable;


	@Test
	void shouldFindOwnersByLastName() {
		Page<Owner> owners = this.owners.findByLastName("Davis", pageable);
		assertThat(owners).hasSize(2);

		owners = this.owners.findByLastName("Daviss", pageable);
		assertThat(owners).isEmpty();
	}

	@Test
	@Transactional
	void shouldInsertOwner() {
		Page<Owner> owners = this.owners.findByLastName("Schultz", pageable);
		int found = (int) owners.getTotalElements();

		Owner owner = new Owner();
		owner.setFirstName("Sam");
		owner.setLastName("Schultz");
		owner.setAddress("4, Evans Street");
		owner.setCity("Wollongong");
		owner.setTelephone("4444444444");
		this.owners.save(owner);
		assertThat(owner.getId().longValue()).isNotEqualTo(0);

		owners = this.owners.findByLastName("Schultz", pageable);
		assertThat(owners.getTotalElements()).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateOwner() {
		Owner owner = this.owners.findById(1);
		String oldLastName = owner.getLastName();
		String newLastName = oldLastName + "X";

		owner.setLastName(newLastName);
		this.owners.save(owner);

		// retrieving new name from database
		owner = this.owners.findById(1);
		assertThat(owner.getLastName()).isEqualTo(newLastName);
	}
}
