package com.github.rshtishi.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.rshtishi.recipe.entity.Recipe;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
	
	public List<Recipe>  findAll();
}
