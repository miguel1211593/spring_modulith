package org.springframework.samples.Owner.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.transaction.annotation.Transactional;

public interface OwnerRepository extends Repository<Owner, Integer> {

	@Query("""
		SELECT DISTINCT owner FROM Owner owner WHERE owner.id =:id
			"""
	)
	@Transactional(readOnly = true)
	Owner findById(@Param("id") Integer id);
	@Query("SELECT DISTINCT owner FROM Owner owner WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	void save(Owner owner);


	@Query("""
		SELECT DISTINCT owner FROM Owner owner WHERE owner.firstName =:firstName AND owner.lastName=:lastName
			"""
	)
	@Transactional(readOnly = true)
	Owner findByName(@Param("firstName") String firstName,@Param("lastName") String lastName);

}
