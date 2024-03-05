package org.springframework.samples.PetType.management;


import lombok.RequiredArgsConstructor;
import org.springframework.samples.PetType.PetTypeInternalAPI;
import org.springframework.samples.PetType.model.PetType;
import org.springframework.samples.PetType.repository.PetTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetTypeManagement implements PetTypeInternalAPI {

	private final PetTypeRepository petTypeRepository;


	public Collection<String> findPetTypes(){
		List<PetType> petTypes = petTypeRepository.findPetTypes();

        return petTypes.stream()
			.map(PetType::getName)
			.collect(Collectors.toList());
	}

	@Override
	public String findPetTypesByPetTypeId(Integer petType_id) {
		return petTypeRepository.findPetTypesById(petType_id).getName();
	}

	@Override
	public Integer findPetTypesByName(String petType) {
		return petTypeRepository.findPetTypesByName(petType).getId();
	}


}
