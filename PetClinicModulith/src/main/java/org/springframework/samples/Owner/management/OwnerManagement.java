package org.springframework.samples.Owner.management;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Owner.OwnerExternalAPI;
import org.springframework.samples.Owner.OwnerInternalAPI;
import org.springframework.samples.Owner.mapper.OwnerMapper;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Owner.repository.OwnerRepository;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetInternalAPI;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerManagement implements OwnerExternalAPI, OwnerInternalAPI {

	private final OwnerRepository repository;
	private final PetInternalAPI petInternalAPI;
	private final OwnerMapper ownerMapper;

	@Override
	public OwnerDTO findById(Integer id) {
		Owner owner = repository.findById(id);
		OwnerDTO ownerDTO = ownerMapper.toOwnerDTO(owner);
		List<PetDTO> petDTOS = petInternalAPI.findPetByOwnerId(id);
		ownerDTO.setPets(petDTOS);

		return ownerDTO;
	}

	@Override
	@Transactional
	public Integer save(OwnerDTO ownerDTO) {
		if (ownerDTO.getId() != null) {
			Owner existingOwner = repository.findById(ownerDTO.getId());
			if (existingOwner != null) {
				updateOwner(existingOwner, ownerDTO);
				return existingOwner.getId();
			}
		}
		Owner newOwner = createOwner(ownerDTO);

		return newOwner.getId();
	}

	private void updateOwner(Owner existingOwner, OwnerDTO ownerDTO) {
		existingOwner.setFirstName(ownerDTO.getFirstName());
		existingOwner.setLastName(ownerDTO.getLastName());
		existingOwner.setAddress(ownerDTO.getAddress());
		existingOwner.setCity(ownerDTO.getCity());
		existingOwner.setTelephone(ownerDTO.getTelephone());
		repository.save(existingOwner);
	}

	private Owner createOwner(OwnerDTO ownerDTO) {
		Owner newOwner = ownerMapper.toOwner(ownerDTO);
		repository.save(newOwner);
		return newOwner;
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

	@Override
	public OwnerDTO findByName(String firstName, String lastName) {
		return convertToDTO(repository.findByName(firstName,lastName));
	}

	private OwnerDTO convertToDTO(Owner owner) {
        return ownerMapper.toOwnerDTO(owner);
	}
}
