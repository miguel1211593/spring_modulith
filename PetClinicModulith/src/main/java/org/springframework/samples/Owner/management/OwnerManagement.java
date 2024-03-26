package org.springframework.samples.Owner.management;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.samples.Owner.OwnerExternalAPI;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Owner.model.OwnerPet;
import org.springframework.samples.Owner.model.OwnerVisit;
import org.springframework.samples.Owner.repository.OwnerRepository;
import org.springframework.samples.Owner.repository.OwnerPetRepository;


import org.springframework.samples.Owner.repository.OwnerVisitRepository;
import org.springframework.samples.Pet.AddPetEvent;
import org.springframework.samples.Pet.EditPetEvent;
import org.springframework.samples.Visit.AddVisitEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class OwnerManagement implements OwnerExternalAPI {

	private final OwnerRepository repository;
	private final OwnerPetRepository petRepository;
	private final OwnerVisitRepository ownerVisitRepository;

	@Override
	public Owner findById(Integer id) {
		Owner owner = repository.findById(id);
		List<OwnerPet> pets = petRepository.findPetByOwnerId(id);
		for (OwnerPet pet: pets){
			Set<OwnerVisit> visits = ownerVisitRepository.findVisitByPetId(pet.getId());
			pet.setVisits(visits);
		}
		owner.setPets(pets);

		return owner;
	}

	@Override
	public Integer save(Owner owner) {
		if (owner.getId() != null) {
			Owner existingOwner = repository.findById(owner.getId());
			existingOwner.setFirstName(owner.getFirstName());
			existingOwner.setLastName(owner.getLastName());
			existingOwner.setAddress(owner.getAddress());
			existingOwner.setCity(owner.getCity());
			existingOwner.setTelephone(owner.getTelephone());
			repository.save(existingOwner);
			return existingOwner.getId();

		}
		Owner newOwner = createOwner(owner);

		return newOwner.getId();
	}

	private Owner createOwner(Owner owner) {
		repository.save(owner);
		return owner;
	}

	@Override
	public Page<Owner> findByLastName(String lastname, Pageable pageable) {
		Page<Owner> pageOwner = repository.findByLastName(lastname,pageable);
		List<Owner> ownerList = new ArrayList<>(pageOwner.getContent());
		for(Owner owner: ownerList) {
			List<OwnerPet> pets = petRepository.findPetByOwnerId(owner.getId());
			owner.setPets(pets);
		}
		return new PageImpl<>(ownerList, pageable, pageOwner.getTotalElements());
	}

	@Override
	public List<OwnerPet> findPetByOwner(Integer owner_id) {
        return petRepository.findPetByOwnerId(owner_id);
	}

	@Override
	public Owner findByName(String firstName, String lastName) {
		return repository.findByName(firstName,lastName);
	}

	@ApplicationModuleListener
	void onNewPetEvent(AddPetEvent event) {
		petRepository.save(new OwnerPet(event.getId(),event.getName(),event.getBirthDate(), event.getOwner_id(), event.getType()));
	}

	@ApplicationModuleListener
	void onEditPetEvent(EditPetEvent event) {
		OwnerPet existingPet = petRepository.findById(event.getId());
		existingPet.setName(event.getName());
		existingPet.setBirthDate(event.getBirthDate());
		existingPet.setType(event.getType());
		petRepository.save(existingPet);
	}

	@ApplicationModuleListener
	void onNewVisitEvent(AddVisitEvent event) {
		ownerVisitRepository.save(new OwnerVisit(event.getId(),event.getPet_id(), event.getDate(),event.getDescription()));
	}
}
