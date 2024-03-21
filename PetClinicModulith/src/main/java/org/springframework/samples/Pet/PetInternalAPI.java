package org.springframework.samples.Pet;

import org.springframework.samples.Pet.model.Pet;

import java.util.List;

public interface PetInternalAPI {

	List<Pet> findPetByOwnerId(Integer owner_id);

}
