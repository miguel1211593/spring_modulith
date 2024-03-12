package org.springframework.samples.Owner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerExternalAPI {
	OwnerDTO findById(Integer id);
	Integer save(OwnerDTO owner);
	Page<OwnerDTO> findByLastName(String lastname, Pageable pageable);

	OwnerDTO findByName(String firstName, String lastName);
}
