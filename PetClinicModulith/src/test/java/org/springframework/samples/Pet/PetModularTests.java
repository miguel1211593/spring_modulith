package org.springframework.samples.Pet;

/*import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.PetType.PetTypeDTO;
import org.springframework.samples.PetType.PetTypeInternalAPI;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

	@Autowired
	protected PetTypeInternalAPI petTypeInternalAPI;

	@Test
	void shouldFindSingleOwnerWithPet() {
		List<Pet> pets = this.petRepository.findPetByOwnerId(1);
		assertThat(pets.get(0).getType()).isNotNull();
		assertThat(petTypeInternalAPI.findPetTypesByPetTypeId(pets.get(0).getType())).isEqualTo("cat");
	}


	@Test
	@Transactional
	void shouldInsertPetIntoDatabaseAndGenerateId() {
		List<Pet> pets = petRepository.findPetByOwnerId(1);
		int found = pets.size();

		Pet pet = new Pet();
		pet.setName("bowser");

		Integer id = petTypeInternalAPI.findPetTypesByName("cat");

		List<PetTypeDTO> petTypes = new ArrayList<>();
		petTypes.add(new PetTypeDTO(1,petTypeInternalAPI.findPetTypesByPetTypeId(id)));

		Optional<PetTypeDTO> chosenPetType = petTypes.stream().findFirst();
        pet.setType(chosenPetType.get().getId());

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


 */
