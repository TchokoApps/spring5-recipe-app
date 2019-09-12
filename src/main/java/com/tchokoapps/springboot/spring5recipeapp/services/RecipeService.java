package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.dto.RecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeDto saveRecipeDto(RecipeDto recipeDto);
    RecipeDto findDtoById(Long id);
    void deleteById(Long id);
}
