package org.springframework.samples.Pet.mapper;

import javax.annotation.processing.Generated;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T14:18:19+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class PetMapperImpl extends PetMapper {

    @Override
    public Pet toPet(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        Pet pet = new Pet();

        pet.setId( petDTO.getId() );
        pet.setName( petDTO.getName() );
        pet.setBirthDate( petDTO.getBirthDate() );
        if ( petDTO.getType() != null ) {
            pet.setType( Integer.parseInt( petDTO.getType() ) );
        }
        pet.setOwner_id( petDTO.getOwner_id() );

        return pet;
    }

    @Override
    public PetDTO toPetDTO(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDTO petDTO = new PetDTO();

        petDTO.setId( pet.getId() );
        petDTO.setName( pet.getName() );
        petDTO.setBirthDate( pet.getBirthDate() );
        if ( pet.getType() != null ) {
            petDTO.setType( String.valueOf( pet.getType() ) );
        }
        petDTO.setOwner_id( pet.getOwner_id() );
        petDTO.setNew( pet.isNew() );

        return petDTO;
    }
}
