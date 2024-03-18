package org.springframework.samples.Visit.mapper;

import org.mapstruct.Mapper;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class VisitMapper {
	public abstract Visit toVisit(VisitDTO visitDTO);
}
