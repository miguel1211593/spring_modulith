package org.springframework.samples.Owner.management;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Owner.OwnerExternalAPI;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Owner.repository.OwnerRepository;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetInternalAPI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerManagement implements OwnerExternalAPI {

	private final OwnerRepository repository;
	private final PetInternalAPI petInternalAPI;

	@Override
	public OwnerDTO findById(Integer id) {
		Owner owner = repository.findById(id);
		OwnerDTO ownerDTO = convertToDTO(owner);
		List<PetDTO> petDTOS = petInternalAPI.findPetByOwnerId(id);
		ownerDTO.setPets(petDTOS);
		return ownerDTO;
	}

	@Override
	public Integer save(OwnerDTO ownerDTO) {
		if (ownerDTO.getId() != null) {
			Owner existingOwner = repository.findById(ownerDTO.getId());
			if (existingOwner != null) {
				existingOwner.setFirstName(ownerDTO.getFirstName());
				existingOwner.setLastName(ownerDTO.getLastName());
				existingOwner.setAddress(ownerDTO.getAddress());
				existingOwner.setCity(ownerDTO.getCity());
				existingOwner.setTelephone(ownerDTO.getTelephone());
				repository.save(existingOwner);
				return existingOwner.getId();
			}
		}
		Owner newOwner = new Owner();
		newOwner.setAddress(ownerDTO.getAddress());
		newOwner.setFirstName(ownerDTO.getFirstName());
		newOwner.setLastName(ownerDTO.getLastName());
		newOwner.setCity(ownerDTO.getCity());
		newOwner.setTelephone(ownerDTO.getTelephone());
		repository.save(newOwner);
		return newOwner.getId();
	}

	@Override
	public Page<OwnerDTO> findByLastName(String lastname, Pageable pageable) {
		Page<Owner> pageOwner = repository.findByLastName(lastname,pageable);
		List<OwnerDTO> ownerDTOList = pageOwner.getContent()
			.stream()
			.map(this::convertToDTO)
			.collect(Collectors.toList());
		return new PageImpl<>(ownerDTOList, pageable, pageOwner.getTotalElements());
	}

	private OwnerDTO convertToDTO(Owner owner) {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(owner.getId());
		ownerDTO.setFirstName(owner.getFirstName());
		ownerDTO.setLastName(owner.getLastName());
		ownerDTO.setAddress(owner.getAddress());
		ownerDTO.setCity(owner.getCity());
		ownerDTO.setTelephone(owner.getTelephone());
		return ownerDTO;
	}
}
