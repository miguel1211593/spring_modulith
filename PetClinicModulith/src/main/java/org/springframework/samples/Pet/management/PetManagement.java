package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.Pet.repository.PetTypeRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PetManagement implements PetExternalAPI {

	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;



	@Override
	public Collection<PetType> findPetTypes() {
        return petTypeRepository.findPetTypes();
	}

	@Override
	public Pet getPetById(Integer petId) {
        return petRepository.findById(petId);
	}

	@Override
	public Pet getPetByName(String name, boolean isNew) {
		return petRepository.findPetByName(name);
	}

	@Override
	public void save(Pet pet) {
		if (pet.getId() != null) {
			Pet existingPet = petRepository.findById(pet.getId());
			existingPet.setName(pet.getName());
			existingPet.setBirthDate(pet.getBirthDate());
			existingPet.setType(pet.getType());
			petRepository.save(existingPet);
		}else{
			petRepository.save(pet);
		}
	}






}
