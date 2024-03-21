/*package org.springframework.samples.PetType;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.samples.PetType.model.PetType;
import org.springframework.samples.PetType.repository.PetTypeRepository;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.STANDALONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PetTypeModularTests {

	@Autowired
	protected PetTypeRepository repository;

	@Test
	void shouldFindAllPetTypes() {
		Collection<PetType> petTypes = this.repository.findPetTypes();

		Optional<PetType> petType1Optional = petTypes.stream()
			.filter(petType -> petType.getId() == 1)
			.findFirst();

		assertThat(petType1Optional).isPresent();
		PetType petType1 = petType1Optional.get();
		assertThat(petType1.getName()).isEqualTo("cat");

		Optional<PetType> petType4Optional = petTypes.stream()
			.filter(petType -> petType.getId() == 4)
			.findAny();

		assertThat(petType4Optional).isPresent();
		PetType petType4 = petType4Optional.get();
		assertThat(petType4.getName()).isEqualTo("snake");
	}
}


 */
