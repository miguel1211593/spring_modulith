package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.samples.Pet.model.PetVisit;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.Pet.repository.PetTypeRepository;
import org.springframework.samples.Pet.repository.PetVisitRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PetManagement implements PetExternalAPI {

	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	private final PetVisitRepository petVisitRepository;



	@Override
	public Collection<PetType> findPetTypes() {
        return petTypeRepository.findPetTypes();
	}

	@Override
	public Pet getPetById(Integer petId) {
		Pet pet = petRepository.findById(petId);
		Set<PetVisit> visit = petVisitRepository.findVisitByPetId(petId);
		pet.setVisits(visit);
		return pet;
	}

	@Override
	public Pet getPetByName(String name, boolean isNew) {
		return petRepository.findPetByName(name);
	}

	@Override
	public void save(Pet pet) {
		if (pet.getId() != null) {
			Pet existingPet = petRepository.findById(pet.getId());
			if (existingPet != null) {
				petRepository.save(existingPet);
			}
		}
		petRepository.save(pet);
	}






}
