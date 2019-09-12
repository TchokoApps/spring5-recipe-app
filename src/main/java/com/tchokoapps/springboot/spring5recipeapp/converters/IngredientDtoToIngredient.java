package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Ingredient;
import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class IngredientDtoToIngredient implements Converter<IngredientDto, Ingredient> {

    private UnitOfMeasureDtoToUnitOfMeasure unitOfMeasureDtoToUnitOfMeasure;

    public IngredientDtoToIngredient(UnitOfMeasureDtoToUnitOfMeasure unitOfMeasureDtoToUnitOfMeasure) {
        this.unitOfMeasureDtoToUnitOfMeasure = unitOfMeasureDtoToUnitOfMeasure;
    }

    @Override
    @Nullable
    @Synchronized
    public Ingredient convert(@NotNull IngredientDto ingredientDto) {
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setAmount(ingredientDto.getAmount());
        ingredient.setDescription(ingredientDto.getDescription());
        ingredient.setUom(ingredientDto.getUom());
        return ingredient;
    }
}