package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
