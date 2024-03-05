package org.springframework.samples.PetType;


import lombok.Data;

@Data
public class PetTypeDTO {
	private Integer id;
	private String name;

	public PetTypeDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
