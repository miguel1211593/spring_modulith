package org.springframework.samples.Pet;

import lombok.Data;
import org.springframework.samples.Visit.VisitDTO;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PetDTO {
	private Integer id;
	private String name;
	private LocalDate birthDate;
	private Set<VisitDTO> visits;
	private String type;
	private Integer owner_id;
	private boolean isNew;

	public PetDTO() {
	}

	public PetDTO(Integer id, String name, LocalDate birthDate, Set<VisitDTO> visits, String type, Integer owner_id) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.visits = visits;
		this.type = type;
		this.owner_id = owner_id;
	}
}
