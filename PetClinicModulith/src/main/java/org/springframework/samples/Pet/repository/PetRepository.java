package org.springframework.samples.Pet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends Repository<Pet, Integer> {
	@Query("""
		SELECT pet FROM Pet pet WHERE pet.owner_id =:id
			"""
	)
	@Transactional(readOnly = true)
	List<Pet> findPetByOwnerId(@Param("id") Integer id);

	@Query("SELECT pet FROM Pet pet WHERE pet.name=:name")
	@Transactional(readOnly = true)
	Pet findPetByName(@Param("name") String name);

	@Query("SELECT pet FROM Pet pet WHERE pet.id=:id")
	@Transactional(readOnly = true)
	Pet findById(@Param("id") Integer id);


	void save(Pet pet);
}
