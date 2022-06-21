package com.github.rshtishi.recipe.service;

import java.util.List;

import com.github.rshtishi.recipe.entity.Recipe;

public interface RecipeService {
	
	public List<Recipe> findAll();
	
	public Recipe findById(int id);
	
	public Recipe update(Recipe recipe);
	
	public Recipe create(Recipe recipe);
	
	public void deleteRecipe(int id);

}
