
package org.springframework.samples.Visit.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.VisitExternalAPI;
import org.springframework.samples.Visit.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
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
	public VisitDTO loadPetWithVisit(@PathVariable("petId") int petId,Map<String, Object> model){
		VisitDTO visitDTO = new VisitDTO();
		model.put("visit", visitDTO);
		return visitDTO;
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,ModelMap model) {

		VisitDTO visitDTO = new VisitDTO();
		model.put("visit", visitDTO);
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	@Transactional
	public String processNewVisitForm(@Valid @RequestBody VisitDTO visit, RedirectAttributes redirectAttributes) {
		visitExternalAPI.save(visit);
		redirectAttributes.addFlashAttribute("message", "Your visit has been booked");
		return "redirect:/owners/{ownerId}";
	}

}
