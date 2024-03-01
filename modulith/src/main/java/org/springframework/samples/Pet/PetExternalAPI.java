package org.springframework.samples.Pet;

import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;

public interface PetExternalAPI {
	Collection<PetType> findPetTypes();

	PetDTO getPetById(Integer petId);
	PetDTO getPetByName(String name, boolean isNew);

	void save(PetDTO petDTO);
}
