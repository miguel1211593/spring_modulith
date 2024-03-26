/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.MonoOwner;
import org.springframework.samples.petclinic.model.MonoPet;
import org.springframework.samples.petclinic.model.MonoPetType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface MonoOwnerRepository extends Repository<MonoOwner, Integer> {

	/**
	 * Retrieve all {@link MonoPetType}s from the data store.
	 * @return a Collection of {@link MonoPetType}s.
	 */
	@Query("SELECT ptype FROM MonoPetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<MonoPetType> findPetTypes();

	/**
	 * Retrieve {@link MonoOwner}s from the data store by last name, returning all owners
	 * whose last name <i>starts</i> with the given name.
	 * @param lastName Value to search for
	 * @return a Collection of matching {@link MonoOwner}s (or an empty Collection if none
	 * found)
	 */

	@Query("SELECT DISTINCT owner FROM MonoOwner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<MonoOwner> findByLastName(@Param("lastName") String lastName, Pageable pageable);

	/**
	 * Retrieve an {@link MonoOwner} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link MonoOwner} if found
	 */
	@Query("SELECT owner FROM MonoOwner owner left join fetch owner.pets WHERE owner.id =:id")
	@Transactional(readOnly = true)
    MonoOwner findById(@Param("id") Integer id);

	/**
	 * Save an {@link MonoOwner} to the data store, either inserting or updating it.
	 * @param owner the {@link MonoOwner} to save
	 */
	void save(MonoOwner owner);

	/**
	 * Returns all the owners from data store
	 **/
	@Query("SELECT owner FROM MonoOwner owner")
	@Transactional(readOnly = true)
	Page<MonoOwner> findAll(Pageable pageable);

	@Query("SELECT pet FROM MonoPet pet WHERE pet.id =:id")
	@Transactional(readOnly = true)
	MonoPet findPetById(@Param("id") Integer id);

}
