package org.springframework.samples.Owner.mapper;

import javax.annotation.processing.Generated;
import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T14:18:19+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class OwnerMapperImpl extends OwnerMapper {

    @Override
    public Owner toOwner(OwnerDTO ownerDTO) {
        if ( ownerDTO == null ) {
            return null;
        }

        Owner owner = new Owner();

        owner.setId( ownerDTO.getId() );
        owner.setAddress( ownerDTO.getAddress() );
        owner.setCity( ownerDTO.getCity() );
        owner.setTelephone( ownerDTO.getTelephone() );
        owner.setFirstName( ownerDTO.getFirstName() );
        owner.setLastName( ownerDTO.getLastName() );

        return owner;
    }

    @Override
    public OwnerDTO toOwnerDTO(Owner owner) {
        if ( owner == null ) {
            return null;
        }

        OwnerDTO ownerDTO = new OwnerDTO();

        ownerDTO.setId( owner.getId() );
        ownerDTO.setFirstName( owner.getFirstName() );
        ownerDTO.setLastName( owner.getLastName() );
        ownerDTO.setAddress( owner.getAddress() );
        ownerDTO.setCity( owner.getCity() );
        ownerDTO.setTelephone( owner.getTelephone() );

        return ownerDTO;
    }
}
