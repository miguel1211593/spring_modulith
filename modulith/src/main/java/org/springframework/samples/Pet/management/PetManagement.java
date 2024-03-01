package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.PetInternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.Visit.AddVisitEvent;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.VisitInternalAPI;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetManagement implements PetInternalAPI, PetExternalAPI {

	private final PetRepository petRepository;
	private final VisitInternalAPI visitInternalApi;

	@Override
	public List<PetDTO> findPetByOwnerId(Integer ownerId) {
		List<Pet> pets = petRepository.findPetByOwnerId(ownerId);
		return pets.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
	}

	@Override
	public Collection<PetType> findPetTypes() {
		return petRepository.findPetTypes();
	}


	@Override
	public PetDTO getPetById(Integer petId) {
		Pet pet = petRepository.findById(petId);
		Set<VisitDTO> visitDTOS = visitInternalApi.findVisitByPetId(petId);
		return new PetDTO(pet.getId(),pet.getName(),pet.getBirthDate(),visitDTOS,pet.getType().getName(),pet.getOwner_id());
	}

	@Override
	public PetDTO getPetByName(String name, boolean isNew) {
		return convertToDTO(petRepository.findPetByName(name));
	}

	@Override
	public void save(PetDTO petDTO) {
		if (petDTO.getId() != null) {
			Pet existingPet = petRepository.findById(petDTO.getId());
			if (existingPet != null) {
				existingPet.setId(petDTO.getId());
				existingPet.setName(petDTO.getName());
				existingPet.setBirthDate(petDTO.getBirthDate());
				existingPet.setOwner_id(petDTO.getOwner_id());
				existingPet.setType(petRepository.findPetTypesByName(petDTO.getType()));
				petRepository.save(existingPet);
			}
		}
		Pet newPet = new Pet();
		newPet.setId(petDTO.getId());
		newPet.setName(petDTO.getName());
		newPet.setBirthDate(petDTO.getBirthDate());
		newPet.setOwner_id(petDTO.getOwner_id());
		newPet.setType(petRepository.findPetTypesByName(petDTO.getType()));
		petRepository.save(newPet);
	}


	private PetDTO convertToDTO(Pet pet) {
		if (pet != null) {
			PetDTO petDTO = new PetDTO();
			petDTO.setId(pet.getId());
			petDTO.setName(pet.getName());
			petDTO.setType(pet.getType().getName());
			petDTO.setBirthDate(pet.getBirthDate());
			petDTO.setOwner_id(pet.getOwner_id());
			return petDTO;
		}
		return null;
	}





}
