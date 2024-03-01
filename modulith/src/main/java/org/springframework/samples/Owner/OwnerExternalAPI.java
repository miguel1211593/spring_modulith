package org.springframework.samples.Owner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.Owner.model.Owner;

public interface OwnerExternalAPI {
	OwnerDTO findById(Integer id);
	Integer save(OwnerDTO owner);
	Page<OwnerDTO> findByLastName(String lastname, Pageable pageable);
}
