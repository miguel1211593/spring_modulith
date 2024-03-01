package org.springframework.samples.Owner;


import lombok.Data;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Pet.PetDTO;

import java.util.List;

@Data
public class OwnerDTO {
	private Integer id;
	private String firstName;
	private String lastName;
	private List<PetDTO> pets;
	private String address;
	private String city;
	private String telephone;
	private boolean isNew;

	public OwnerDTO(Integer id, String firstName, String lastName, List<PetDTO> pets, String address, String city, String telephone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pets = pets;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

	public OwnerDTO() {
	}


	public Owner getModel() {
		Owner newOwner = new Owner();
		newOwner.setAddress(this.address);
		newOwner.setFirstName(this.firstName);
		newOwner.setLastName(this.lastName);
		newOwner.setCity(this.city);
		newOwner.setTelephone(this.telephone);
		return newOwner;
	}
}


