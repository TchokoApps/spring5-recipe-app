package com.tchokoapps.springboot.spring5recipeapp.repositories;

import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
