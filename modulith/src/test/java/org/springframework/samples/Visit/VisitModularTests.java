package org.springframework.samples.Visit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.samples.Visit.repository.VisitRepository;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ApplicationModuleTest(ApplicationModuleTest.BootstrapMode.STANDALONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class VisitModularTests {

	@Autowired
	protected VisitRepository visitRepository;

	@Test
	@Transactional
	void shouldAddNewVisitForPet() {

		Set<Visit> visits = visitRepository.findVisitByPetId(1);
		int found = visits.size();

		Visit visit = new Visit();
		visit.setDescription("test");
		visit.setPet_id(1);

		visits.add(visit);
		this.visitRepository.save(visit);

		Set<Visit> visits2 = visitRepository.findVisitByPetId(1);

		assertThat(visits2)
			.hasSize(found + 1)
			.allMatch(value -> value.getId() != null);
	}

	@Test
	void shouldFindVisitsByPetId() {
		Collection<Visit> visits = visitRepository.findVisitByPetId(7);

		assertThat(visits) //
			.hasSize(4) //
			.element(0)
			.extracting(Visit::getDate)
			.isNotNull();
	}
}
