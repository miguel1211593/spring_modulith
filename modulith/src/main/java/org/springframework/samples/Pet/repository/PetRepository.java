package org.springframework.samples.Pet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PetRepository extends Repository<Pet, Integer> {
	@Query("""
		SELECT pet FROM Pet pet WHERE pet.owner_id =:id
			"""
	)
	@Transactional(readOnly = true)
	List<Pet> findPetByOwnerId(@Param("id") Integer id);

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();

	@Query("SELECT ptype FROM PetType ptype WHERE ptype.name=:name")
	@Transactional(readOnly = true)
	PetType findPetTypesByName(@Param("name") String name);

	@Query("SELECT pet FROM Pet pet WHERE pet.id=:id")
	@Transactional(readOnly = true)
	Pet findPetById(@Param("id") Integer id);

	@Query("SELECT pet FROM Pet pet WHERE pet.name=:name")
	@Transactional(readOnly = true)
	Pet findPetByName(@Param("name") String name);

	@Query("SELECT pet FROM Pet pet WHERE pet.id=:id")
	@Transactional(readOnly = true)
	Pet findById(@Param("id") Integer id);


	void save(Pet pet);

	void save(PetDTO petDTO);

	@Query("SELECT ptype FROM PetType ptype WHERE ptype.name=:name")
	@Transactional(readOnly = true)
	List<PetType> findListPetTypesByName(@Param("name") String name);
}
