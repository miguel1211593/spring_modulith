package org.springframework.samples.Visit;


import java.util.List;
import java.util.Set;

public interface VisitExternalAPI {

	void save(VisitDTO visitDTO);
	List<VisitDTO> findAll();
}
