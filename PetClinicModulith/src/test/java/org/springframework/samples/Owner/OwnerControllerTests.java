/*package org.springframework.samples.Owner;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.Owner.api.OwnerController;
import org.springframework.samples.Pet.PetDTO;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OwnerController.class)
@DisabledInNativeImage
@DisabledInAotMode
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OwnerControllerTests {

	private static final int TEST_OWNER_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OwnerExternalAPI ownerExternalAPI;

	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/createOrUpdateOwnerForm"));
	}

	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc
			.perform(post("/owners/new").param("firstName", "Joe")
				.param("lastName", "Bloggs")
				.param("address", "123 Caramel Street")
				.param("city", "London")
				.param("telephone", "01316761638"))
			.andExpect(status().is3xxRedirection());
	}

	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/owners/find"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("owner"))
			.andExpect(view().name("owners/findOwners"));
	}

/*
	@Test
	void testProcessFindFormByLastName() throws Exception {
		Page<OwnerDTO> tasks = new PageImpl<OwnerDTO>(Lists.newArrayList(george()));
		Mockito.when(this.ownerExternalAPI.findByLastName(eq("Franklin"), any(Pageable.class))).thenReturn(tasks);
		mockMvc.perform(get("/owners?page=1").param("lastName", "Franklin"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/" + TEST_OWNER_ID));
	}

	@Test
	void testProcessUpdateOwnerFormSuccess() throws Exception {
		mockMvc
			.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID).param("firstName", "Joe")
				.param("lastName", "Bloggs")
				.param("address", "123 Caramel Street")
				.param("city", "London")
				.param("telephone", "01616291589"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}


	@Test
	void testProcessUpdateOwnerFormUnchangedSuccess() throws Exception {
		mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/owners/{ownerId}"));
	}

	@Test


	void testShowOwner() throws Exception {
		PetDTO petDTO = new PetDTO(1,"mmm",LocalDate.parse("2021-11-11", java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")),null,"cat",1);
		List<PetDTO> petDTOS = new ArrayList<>();
		petDTOS.add(petDTO);
		OwnerDTO ownerDTO = new OwnerDTO(1,"George","Franklin",petDTOS,"110 W. Liberty St.","Madison","6085551023");
		ownerExternalAPI.save(ownerDTO);

		mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attribute("ownerDTO", hasProperty("lastName", is("Franklin"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("firstName", is("George"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("address", is("110 W. Liberty St."))))
			.andExpect(model().attribute("ownerDTO", hasProperty("city", is("Madison"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("telephone", is("6085551023"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("pets", not(empty()))))
			.andExpect(model().attribute("ownerDTO", hasProperty("pets", new BaseMatcher<List<PetDTO>>() {

				@Override
				public boolean matches(Object item) {
					@SuppressWarnings("unchecked")
					List<PetDTO> pets = (List<PetDTO>) item;
					PetDTO pet = pets.get(0);
					if (pet.getVisits().isEmpty()) {
						return false;
					}
					return true;
				}

				@Override
				public void describeTo(Description description) {
					description.appendText("Max did not have any visits");
				}
			})))
			.andExpect(view().name("owners/ownerDetails"));
	}
}

 */
