package org.springframework.samples.Visit;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitDTO {
	private Integer visit_id;
	private String description;
	private Integer pet_id;
	private LocalDate date;

	public VisitDTO(Integer visit_id, String description, Integer pet_id, LocalDate date) {
		this.visit_id = visit_id;
		this.description = description;
		this.pet_id = pet_id;
		this.date = date;
	}

	public VisitDTO() {
	}
}
