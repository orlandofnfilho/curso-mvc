package com.github.rshtishi.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.rshtishi.recipe.entity.Recipe;
import com.github.rshtishi.recipe.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe findById(int id) {
		Recipe recipe = recipeRepository.findById(id).orElse(null);
		return recipe;
	}

	@Override
	public Recipe update(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public Recipe create(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void deleteRecipe(int id) {
		recipeRepository.deleteById(id);
	}

}
