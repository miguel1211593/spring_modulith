
package org.springframework.samples.Pet.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "types")
public class PetType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Override
	public String toString() {
		return this.getName();
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
}
