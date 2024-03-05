package org.springframework.samples.PetType.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.PetType.model.PetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetTypeRepository extends Repository<PetType, Integer> {

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();

	@Query("SELECT ptype FROM PetType ptype WHERE ptype.name=:name")
	@Transactional(readOnly = true)
	PetType findPetTypesByName(@Param("name") String name);

	@Query("SELECT ptype FROM PetType ptype WHERE ptype.id=:id")
	@Transactional(readOnly = true)
	PetType findPetTypesById(@Param("id") Integer id);

	@Query("SELECT ptype FROM PetType ptype WHERE ptype.name=:name")
	@Transactional(readOnly = true)
	List<PetType> findListPetTypesByName(@Param("name") String name);
}
