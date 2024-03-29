
package org.springframework.samples.Visit.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.Visit.VisitExternalAPI;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class VisitController {

	private final VisitExternalAPI visitExternalAPI;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("petId") int petId,Map<String, Object> model){
		Visit visit = new Visit();
		model.put("visit", visit);
		return visit;
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,ModelMap model) {

		Visit visit = new Visit();
		model.put("visit", visit);
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	@Transactional
	public String processNewVisitForm(@PathVariable("petId") int petId, @Valid Visit visit, RedirectAttributes redirectAttributes) {
		visit.setPet_id(petId);
		visitExternalAPI.save(visit);
		redirectAttributes.addFlashAttribute("message", "Your visit has been booked");
		return "redirect:/owners/{ownerId}";
	}

}
