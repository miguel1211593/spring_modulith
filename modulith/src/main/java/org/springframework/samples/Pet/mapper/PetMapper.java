package org.springframework.samples.Pet.mapper;


import org.mapstruct.Mapper;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PetMapper {
	public abstract Pet toPet(PetDTO petDTO);

	public abstract PetDTO toPetDTO(Pet pet);
}
