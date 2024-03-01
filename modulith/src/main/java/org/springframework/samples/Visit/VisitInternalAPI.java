package org.springframework.samples.Visit;

import java.util.List;
import java.util.Set;

public interface VisitInternalAPI {


	Set<VisitDTO> findVisitByPetId(int pet_id);

}
