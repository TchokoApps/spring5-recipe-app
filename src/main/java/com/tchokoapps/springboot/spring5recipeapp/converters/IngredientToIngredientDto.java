package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class IngredientToIngredientDto implements Converter<Ingredient, IngredientDto> {
    private final UnitOfMeasureToUnitOfMeasureDto uomConverter;

    public IngredientToIngredientDto(UnitOfMeasureToUnitOfMeasureDto uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDto convert(@NotNull Ingredient ingredient) {
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());

        if(ingredient.getRecipe() != null) {
            ingredientDto.setRecipeId(ingredient.getRecipe().getId());
        }

        ingredientDto.setAmount(ingredient.getAmount());
        ingredientDto.setDescription(ingredient.getDescription());
        ingredientDto.setUom(uomConverter.convert(ingredient.getUom()));
        return ingredientDto;
    }
}
