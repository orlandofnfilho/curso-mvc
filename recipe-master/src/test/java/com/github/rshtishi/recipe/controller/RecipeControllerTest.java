package com.github.rshtishi.recipe.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testShowRecipes() throws Exception {
		mockMvc.perform(get("/recipes")).andExpect(status().isOk()).andExpect(view().name("recipes"))
				.andExpect(content().string(containsString("Recipes")));
	}

	@Test
	public void testViewRecipe() throws Exception {
		mockMvc.perform(get("/recipes/view/1")).andExpect(status().isOk()).andExpect(view().name("view-recipe"))
				.andExpect(content().string(containsString("View Recipe")));
	}

	@Test
	public void testShowNewRecipeForm() throws Exception {
		mockMvc.perform(get("/recipes/new")).andExpect(status().isOk()).andExpect(view().name("new-recipe"))
				.andExpect(content().string(containsString("Add New Recipe")));
	}

	@Test
	public void testShowEditRecipeForm() throws Exception {
		mockMvc.perform(get("/recipes/edit/1")).andExpect(status().isOk()).andExpect(view().name("edit-recipe"))
				.andExpect(content().string(containsString("Edit Recipe")));
	}

	@Test
	public void testDeleteRecipe() throws Exception {
		mockMvc.perform(get("/recipes/delete/2")).andExpect(status().isOk()).andExpect(view().name("recipes"))
				.andExpect(content().string(containsString("Recipes")));
	}

}
