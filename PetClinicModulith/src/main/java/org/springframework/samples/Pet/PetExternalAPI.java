package org.springframework.samples.Pet;

import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;

import java.util.Collection;

public interface PetExternalAPI {
	Collection<PetType> findPetTypes();

	Pet getPetById(Integer petId);
	Pet getPetByName(String name, boolean isNew);

	void save(Pet pet);
}
