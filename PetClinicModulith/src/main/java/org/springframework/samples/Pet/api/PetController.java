package org.springframework.samples.Pet.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Pet.model.Pet;
import org.springframework.samples.Pet.model.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor
public class PetController {
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final PetExternalAPI petExternalAPI;

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.petExternalAPI.findPetTypes();
	}

	@ModelAttribute("pet1")
	public Pet findPet(@PathVariable(name = "petId", required = false) Integer petId) {
		if (petId == null) {
			return new Pet();
		}
        return petExternalAPI.getPetById(petId);
	}

	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/pets/new")
	public String initCreationForm(ModelMap model) {
		Pet pet = new Pet();
		model.put("pet1", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/new")
	public String processCreationForm(@PathVariable("ownerId") int ownerId, @Valid Pet pet, BindingResult result, ModelMap model,
									  RedirectAttributes redirectAttributes) {

		if (StringUtils.hasText(pet.getName()) && pet.isNew() && petExternalAPI.getPetByName(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		pet.setOwner_id(ownerId);

		petExternalAPI.save(pet);
		redirectAttributes.addFlashAttribute("message", "New Pet has been Added");
		return "redirect:/owners/{ownerId}";
	}

	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model,
								 RedirectAttributes redirectAttributes) {
		Pet pet = petExternalAPI.getPetById(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@PathVariable("ownerId") int ownerId,@Valid Pet pet, BindingResult result,ModelMap model,
									RedirectAttributes redirectAttributes) {

		String petName = pet.getName();

		if (StringUtils.hasText(petName)) {
			Pet existingPet = petExternalAPI.getPetByName(petName.toLowerCase(), false);
			if (existingPet != null && !Objects.equals(existingPet.getId(), pet.getId())) {
				result.rejectValue("name", "duplicate", "already exists");
			}
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		pet.setOwner_id(ownerId);

		petExternalAPI.save(pet);
		redirectAttributes.addFlashAttribute("message", "Pet details has been edited");
		return "redirect:/owners/{ownerId}";
	}
}
