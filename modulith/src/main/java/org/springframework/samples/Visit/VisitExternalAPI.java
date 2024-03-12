package org.springframework.samples.Visit;


import org.springframework.samples.Visit.model.Visit;

import java.util.List;

public interface VisitExternalAPI {

	void save(VisitDTO visitDTO);
	List<VisitDTO> findAll();
}
