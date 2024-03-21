package org.springframework.samples.Visit.management;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.Visit.VisitExternalAPI;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.samples.Visit.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitManagement implements VisitExternalAPI {

	private final VisitRepository visitRepository;


	@Override
	public void save(Visit visit) {
		visitRepository.save(visit);
	}

	@Override
	public List<Visit> findAll() {
        return visitRepository.findAll();
	}


}
