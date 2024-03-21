package org.springframework.samples.Owner.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Owner.model.OwnerPet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerPetRepository extends Repository<OwnerPet, Integer> {
	@Query("""
		SELECT pet FROM OwnerPet pet WHERE pet.owner_id =:id
			"""
	)
	@Transactional(readOnly = true)
	List<OwnerPet> findPetByOwnerId(@Param("id") Integer id);


	@Query("SELECT pet FROM OwnerPet pet WHERE pet.id=:id")
	@Transactional(readOnly = true)
	OwnerPet findById(@Param("id") Integer id);


	void save(OwnerPet pet);
}
