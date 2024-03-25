/*package org.springframework.samples.Owner;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.samples.Owner.api.OwnerController;
import org.springframework.samples.Owner.model.Owner;
import org.springframework.samples.Owner.model.OwnerPet;
import org.springframework.samples.Owner.model.OwnerPetType;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.google.common.collect.Lists;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.mockito.ArgumentMatchers.eq;
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

    private Owner george() {
        Owner george = new Owner();
        george.setId(TEST_OWNER_ID);
        george.setFirstName("George");
        george.setLastName("Franklin");
        george.setAddress("110 W. Liberty St.");
        george.setCity("Madison");
        george.setTelephone("6085551023");
        List<OwnerPet> pets = new ArrayList<>();
        OwnerPet max = new OwnerPet();
        OwnerPetType dog = new OwnerPetType();
        dog.setName("dog");
        max.setType(dog);
        max.setName("Max");
        max.setBirthDate(LocalDate.now());
        max.setId(1);
        pets.add(max);
        george.setPets(pets);
        return george;
    }


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


	@Test
	void testProcessFindFormByLastName() throws Exception {
		Page<Owner> tasks = new PageImpl<Owner>(Lists.newArrayList(george()));
		Mockito.when(this.ownerExternalAPI.findByLastName(eq("Franklin"), (org.springframework.data.domain.Pageable) any(Pageable.class))).thenReturn(tasks);
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
        OwnerPetType petType = new OwnerPetType(6,"horse");
		OwnerPet petDTO = new OwnerPet(1,"mmm",LocalDate.parse("2021-11-11", java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")),1,petType);
		List<OwnerPet> petDTOS = new ArrayList<>();
		petDTOS.add(petDTO);
		Owner ownerDTO = new Owner(1,"George","Franklin","110 W. Liberty St.","Madison","6085551023",petDTOS);
		ownerExternalAPI.save(ownerDTO);

		mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
			.andExpect(status().isOk())
			.andExpect(model().attribute("ownerDTO", hasProperty("lastName", is("Franklin"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("firstName", is("George"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("address", is("110 W. Liberty St."))))
			.andExpect(model().attribute("ownerDTO", hasProperty("city", is("Madison"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("telephone", is("6085551023"))))
			.andExpect(model().attribute("ownerDTO", hasProperty("pets", not(empty()))))
			.andExpect(model().attribute("ownerDTO", hasProperty("pets", new BaseMatcher<List<OwnerPet>>() {

				@Override
				public boolean matches(Object item) {
					@SuppressWarnings("unchecked")
					List<OwnerPet> pets = (List<OwnerPet>) item;
                    OwnerPet pet = pets.get(0);
                    return !pet.getVisits().isEmpty();
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

