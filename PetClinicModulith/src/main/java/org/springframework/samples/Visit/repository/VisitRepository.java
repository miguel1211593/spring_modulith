package org.springframework.samples.Visit.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface VisitRepository extends Repository<Visit, Integer> {

	void save(Visit visit);

	@Query("SELECT visit FROM Visit visit WHERE visit.pet_id=:id")
	@Transactional(readOnly = true)
	Set<Visit> findVisitByPetId(@Param("id") Integer id);

	@Query("SELECT visit FROM Visit visit")
	@Transactional(readOnly = true)
	List<Visit> findAll();

}
