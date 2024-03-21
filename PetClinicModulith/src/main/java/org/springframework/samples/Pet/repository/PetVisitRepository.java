package org.springframework.samples.Pet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Pet.model.PetVisit;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface PetVisitRepository extends Repository<PetVisit, Integer> {

	void save(PetVisit visit);

	@Query("SELECT visit FROM PetVisit visit WHERE visit.pet_id=:id")
	@Transactional(readOnly = true)
	Set<PetVisit> findVisitByPetId(@Param("id") Integer id);


}
