package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Ingredient;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class IngredientDtoToIngredient implements Converter<IngredientDto, Ingredient> {

    private UnitOfMeasureDtoToUnitOfMeasure unitOfMeasureToUnitOfMeasureDto;

    public IngredientDtoToIngredient(UnitOfMeasureDtoToUnitOfMeasure unitOfMeasureToUnitOfMeasureDto) {
        this.unitOfMeasureToUnitOfMeasureDto = unitOfMeasureToUnitOfMeasureDto;
    }

    @Override
    @Nullable
    @Synchronized
    public Ingredient convert(@NotNull IngredientDto ingredientDto) {
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());

        if(ingredientDto.getRecipeId() != null) {
            Recipe recipe = new Recipe();
            recipe.setId(ingredientDto.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(ingredientDto.getAmount());
        ingredient.setDescription(ingredientDto.getDescription());
        ingredient.setUom(unitOfMeasureToUnitOfMeasureDto.convert(ingredientDto.getUom()));
        return ingredient;
    }
}