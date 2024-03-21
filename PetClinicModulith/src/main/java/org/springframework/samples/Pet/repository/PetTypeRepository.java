package org.springframework.samples.Pet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetTypeRepository extends Repository<PetType, Integer> {

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();

}
