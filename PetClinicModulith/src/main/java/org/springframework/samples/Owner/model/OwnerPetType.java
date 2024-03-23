
package org.springframework.samples.Owner.model;

import jakarta.persistence.*;


@Entity
@Table(name = "types")
public class OwnerPetType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name")
	private String name;

	public OwnerPetType(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public OwnerPetType() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
