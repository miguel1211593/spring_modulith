package org.springframework.samples.Visit.management;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.VisitExternalAPI;
import org.springframework.samples.Visit.VisitInternalAPI;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.samples.Visit.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitManagement implements VisitExternalAPI, VisitInternalAPI {

	private final VisitRepository visitRepository;


	@Override
	public void save(VisitDTO visitDTO) {
		Visit newVisit = new Visit();
		newVisit.setDate(visitDTO.getDate());
		newVisit.setDescription(visitDTO.getDescription());
		newVisit.setPet_id(visitDTO.getPet_id());
		visitRepository.save(newVisit);
		List<Visit> visits = visitRepository.findAll();
		for (Visit visit : visits) {
			System.out.println("Visit ID: " + visit.getId());
			System.out.println("Visit Date: " + visit.getDate());
			System.out.println("Visit Description: " + visit.getDescription());
			System.out.println("Pet: " + visit.getPet_id());

		}
	}

	@Override
	public List<VisitDTO> findAll() {
		List<Visit> visits = visitRepository.findAll();
		return visits.stream()
			.map(visit -> new VisitDTO(visit.getId(),visit.getDescription(), visit.getPet_id(), visit.getDate() ))
			.collect(Collectors.toList());
	}

	@Override
	public Set<VisitDTO> findVisitByPetId(int pet_id) {
		return convertToDTO(visitRepository.findVisitByPetId(pet_id));
	}

	private Set<VisitDTO> convertToDTO(Set<Visit> visits) {
		Set<VisitDTO> visitDTOList = new HashSet<>();
		for (Visit visit : visits) {
			VisitDTO visitDTO = new VisitDTO();
			visitDTO.setVisit_id(visit.getId());
			visitDTO.setDate(visit.getDate());
			visitDTO.setDescription(visit.getDescription());
			visitDTO.setPet_id(visit.getPet_id());
			visitDTOList.add(visitDTO);
		}
		return visitDTOList;
	}

}
