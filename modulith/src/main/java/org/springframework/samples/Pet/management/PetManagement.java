package org.springframework.samples.Pet.management;

import lombok.RequiredArgsConstructor;

import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.PetInternalAPI;
import org.springframework.samples.Pet.mapper.PetMapper;
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
	private final PetMapper petMapper;

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
        return petTypeInternalAPI.findPetTypes();
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
				existingPet = petMapper.toPet(petDTO);
				petRepository.save(existingPet);
			}
		}
		Integer petTypeId = petTypeInternalAPI.findPetTypesByName(petDTO.getType());
		petDTO.setType(String.valueOf(petTypeId));
		Pet pet = petMapper.toPet(petDTO);
		petRepository.save(pet);
	}




	private PetDTO convertToDTO(Pet pet) {
		if (pet != null) {
			PetDTO petDTO = petMapper.toPetDTO(pet);
			petDTO.setType(petTypeInternalAPI.findPetTypesByPetTypeId(pet.getType()));
			return petDTO;
		}
		return null;
	}





}
