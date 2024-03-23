package org.springframework.samples.Owner.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "pets")
public class OwnerPet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	public OwnerPet(Integer id, String name, LocalDate birthDate, Integer owner_id, OwnerPetType type) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.owner_id = owner_id;
		this.type = type;
	}

	public OwnerPet() {

	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@Column(name = "owner_id")
	private Integer owner_id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_id")
	private Set<OwnerVisit> visits = new LinkedHashSet<>() ;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private OwnerPetType type;


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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public Set<OwnerVisit> getVisits() {
		return visits;
	}

	public void setVisits(Set<OwnerVisit> visits) {
		this.visits = visits;
	}

	public void setType(OwnerPetType type) {
		this.type = type;
	}

	public OwnerPetType getType() {
		return type;
	}

	public boolean isNew() {
		return this.id == null;
	}
}
