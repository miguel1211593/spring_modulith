package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.samples.Pet.AddPetEvent;
import org.springframework.samples.Pet.EditPetEvent;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.Pet.repository.PetTypeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PetManagement implements PetExternalAPI {

	private final PetRepository petRepository;
	private final PetTypeRepository petTypeRepository;
	private final ApplicationEventPublisher events;



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
	@Transactional
	public void save(Pet pet) {
		if (pet.getId() != null) {
			Pet existingPet = petRepository.findById(pet.getId());
			existingPet.setName(pet.getName());
			existingPet.setBirthDate(pet.getBirthDate());
			existingPet.setType(pet.getType());
			events.publishEvent(new EditPetEvent(pet.getId(),pet.getName(),pet.getBirthDate(),pet.getType().getId(),pet.getType().getName(),pet.getOwner_id()));
			petRepository.save(existingPet);
		}else{
			events.publishEvent(new AddPetEvent(pet.getId(),pet.getName(),pet.getBirthDate(),pet.getType().getId(),pet.getType().getName(),pet.getOwner_id()));
			petRepository.save(pet);
		}
	}






}
