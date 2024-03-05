package org.springframework.samples.PetType;

import java.util.Collection;

public interface PetTypeInternalAPI {

	Collection<String> findPetTypes();
	String findPetTypesByPetTypeId(Integer petType_id);

	Integer findPetTypesByName(String petType);
}
