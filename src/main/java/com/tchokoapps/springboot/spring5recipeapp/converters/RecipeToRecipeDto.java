package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.RecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Category;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class RecipeToRecipeDto implements Converter<Recipe, RecipeDto> {
    private final CategoryToCategoryDto categoryConveter;
    private final IngredientToIngredientDto ingredientConverter;
    private final NotesToNotesDto notesConverter;

    public RecipeToRecipeDto(CategoryToCategoryDto categoryConveter, IngredientToIngredientDto ingredientConverter,
                                 NotesToNotesDto notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeDto convert(@NotNull Recipe source) {

        final RecipeDto Dto = new RecipeDto();
        Dto.setId(source.getId());
        Dto.setCookTime(source.getCookTime());
        Dto.setPrepTime(source.getPrepTime());
        Dto.setDescription(source.getDescription());
        Dto.setDifficulty(source.getDifficulty());
        Dto.setDirections(source.getDirections());
        Dto.setServings(source.getServings());
        Dto.setSource(source.getSource());
        Dto.setUrl(source.getUrl());
        Dto.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> Dto.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> Dto.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return Dto;
    }
}
