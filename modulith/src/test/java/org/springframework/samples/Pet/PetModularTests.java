package org.springframework.samples.Pet;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PetModularTests {

	@Autowired
	protected PetRepository petRepository;



	@Test
	void shouldFindSingleOwnerWithPet() {
		List<Pet> pets = this.petRepository.findPetByOwnerId(1);
		assertThat(pets).hasSize(1);
		assertThat(pets.get(0).getType()).isNotNull();
		assertThat(pets.get(0).getType().getName()).isEqualTo("cat");
	}


	@Test
	void shouldFindAllPetTypes() {
		Collection<PetType> petTypes = this.petRepository.findPetTypes();

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


	@Test
	@Transactional
	void shouldInsertPetIntoDatabaseAndGenerateId() {
		List<Pet> pets = petRepository.findPetByOwnerId(1);
		int found = pets.size();

		Pet pet = new Pet();
		pet.setName("bowser");

		List<PetType> petTypes =  petRepository.findListPetTypesByName("cat");

		Optional<PetType> chosenPetType = petTypes.stream().findFirst();
		if (chosenPetType.isPresent()) {
			pet.setType(chosenPetType.get());
		} else {
			throw new IllegalStateException("Unexpected number of PetTypes found for name 'cat'");
		}

		pet.setBirthDate(LocalDate.now());
		pets.add(pet);
		assertThat(pets.size()).isEqualTo(found + 1);

		this.petRepository.save(pet);

		pet = petRepository.findPetByName("bowser");
		assertThat(pet.getId()).isNotNull();
	}

	@Test
	@Transactional
	void shouldUpdatePetName() {
		List<Pet> pets = petRepository.findPetByOwnerId(1);
		Pet newPet = pets.get(0);
		String oldName = newPet.getName();

		String newName = oldName + "X";
		newPet.setName(newName);
		this.petRepository.save(newPet);

		List<Pet> pets1 = petRepository.findPetByOwnerId(1);
		assertThat(pets1.get(0).getName()).isEqualTo(newName);
	}
}
