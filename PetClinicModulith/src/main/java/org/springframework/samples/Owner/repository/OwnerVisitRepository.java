package org.springframework.samples.Owner.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Owner.model.OwnerVisit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface OwnerVisitRepository extends Repository<OwnerVisit, Integer> {

	void save(OwnerVisit visit);

	@Query("SELECT visit FROM OwnerVisit visit WHERE visit.pet_id=:id")
	@Transactional(readOnly = true)
	Set<OwnerVisit> findVisitByPetId(@Param("id") Integer id);

}
