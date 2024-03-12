package org.springframework.samples.Visit.mapper;

import javax.annotation.processing.Generated;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T14:18:19+0000",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class VisitMapperImpl extends VisitMapper {

    @Override
    public Visit toVisit(VisitDTO visitDTO) {
        if ( visitDTO == null ) {
            return null;
        }

        Visit visit = new Visit();

        visit.setDate( visitDTO.getDate() );
        visit.setDescription( visitDTO.getDescription() );
        visit.setPet_id( visitDTO.getPet_id() );

        return visit;
    }
}
