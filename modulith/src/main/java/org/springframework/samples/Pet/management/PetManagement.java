package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.PetInternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.repository.PetRepository;
import org.springframework.samples.PetType.PetTypeDTO;
import org.springframework.samples.PetType.PetTypeInternalAPI;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.VisitInternalAPI;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetManagement implements PetInternalAPI, PetExternalAPI {

	private final PetRepository petRepository;
	private final VisitInternalAPI visitInternalApi;
	private final PetTypeInternalAPI petTypeInternalAPI;

	@Override
	public List<PetDTO> findPetByOwnerId(Integer ownerId) {
		List<Pet> pets = petRepository.findPetByOwnerId(ownerId);
		return pets.stream()
			.map(this::convertToDTO)
			.map(this::createPetDTOWithVisits)
			.collect(Collectors.toList());
	}

	private PetDTO createPetDTOWithVisits(PetDTO pet) {
		Set<VisitDTO> visitDTOS = visitInternalApi.findVisitByPetId(pet.getId());
		return new PetDTO(pet.getId(), pet.getName(), pet.getBirthDate(), visitDTOS, pet.getType(), pet.getOwner_id());
	}

	@Override
	public Collection<String> findPetTypesByName() {
		Collection<String> collection = petTypeInternalAPI.findPetTypes();

		return collection;
	}

	@Override
	public PetDTO getPetById(Integer petId) {
		Pet pet = petRepository.findById(petId);
		Set<VisitDTO> visitDTOS = visitInternalApi.findVisitByPetId(petId);
		String petType = petTypeInternalAPI.findPetTypesByPetTypeId(pet.getType());
		return new PetDTO(pet.getId(),pet.getName(),pet.getBirthDate(),visitDTOS,petType,pet.getOwner_id());
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
				existingPet.setType(petTypeInternalAPI.findPetTypesByName(petDTO.getType()));
				petRepository.save(existingPet);
			}
		}
		Pet newPet = new Pet();
		newPet.setId(petDTO.getId());
		newPet.setName(petDTO.getName());
		newPet.setBirthDate(petDTO.getBirthDate());
		newPet.setOwner_id(petDTO.getOwner_id());
		newPet.setType(petTypeInternalAPI.findPetTypesByName(petDTO.getType()));
		petRepository.save(newPet);
	}


	private PetDTO convertToDTO(Pet pet) {
		if (pet != null) {
			PetDTO petDTO = new PetDTO();
			petDTO.setId(pet.getId());
			petDTO.setName(pet.getName());
			petDTO.setType(petTypeInternalAPI.findPetTypesByPetTypeId(pet.getType()));
			petDTO.setBirthDate(pet.getBirthDate());
			petDTO.setOwner_id(pet.getOwner_id());
			return petDTO;
		}
		return null;
	}





}
