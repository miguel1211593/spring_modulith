package org.springframework.samples.Pet;

import java.util.Collection;

public interface PetExternalAPI {
	Collection<String> findPetTypesByName();

	PetDTO getPetById(Integer petId);
	PetDTO getPetByName(String name, boolean isNew);

	void save(PetDTO petDTO);
}
