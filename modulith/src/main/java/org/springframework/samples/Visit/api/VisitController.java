/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.Visit.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.samples.Owner.OwnerDTO;
import org.springframework.samples.Owner.OwnerExternalAPI;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.samples.Pet.PetExternalAPI;
import org.springframework.samples.Visit.AddVisitEvent;
import org.springframework.samples.Visit.VisitDTO;
import org.springframework.samples.Visit.VisitExternalAPI;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class VisitController {

	private final VisitExternalAPI visitExternalAPI;

	private final ApplicationEventPublisher eventPublisher;


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
	public String processNewVisitForm(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,@Valid VisitDTO visit,
									  BindingResult result, RedirectAttributes redirectAttributes) {

		List<VisitDTO> visitDTOS = visitExternalAPI.findAll();
		visit.setVisit_id(visitDTOS.size() + 1);
		visit.setPet_id(petId);
		visitExternalAPI.save(visit);
		redirectAttributes.addFlashAttribute("message", "Your visit has been booked");
		return "redirect:/owners/{ownerId}";
	}

}
