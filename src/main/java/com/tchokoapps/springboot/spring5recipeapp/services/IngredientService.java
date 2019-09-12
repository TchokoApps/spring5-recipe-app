package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Ingredient;

public interface IngredientService {
    IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientDto saveIngredientDto(IngredientDto ingredientDto);
}
