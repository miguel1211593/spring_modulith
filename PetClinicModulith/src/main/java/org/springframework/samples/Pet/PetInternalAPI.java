package org.springframework.samples.Pet;

import java.util.List;

public interface PetInternalAPI {

	List<PetDTO> findPetByOwnerId(Integer owner_id);

}
