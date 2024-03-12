package org.springframework.samples.Owner.mapper;

import org.mapstruct.Mapper;
import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class OwnerMapper {
	public abstract Owner toOwner(OwnerDTO ownerDTO);

	public abstract OwnerDTO toOwnerDTO(Owner owner);

}
