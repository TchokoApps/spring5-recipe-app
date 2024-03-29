package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.RecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class RecipeDtoToRecipe implements Converter<RecipeDto, Recipe> {
    private final CategoryDtoToCategory categoryConveter;
    private final IngredientDtoToIngredient ingredientConverter;
    private final NotesDtoToNotes notesConverter;

    public RecipeDtoToRecipe(CategoryDtoToCategory categoryConveter, IngredientDtoToIngredient ingredientConverter,
                                 NotesDtoToNotes notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(@NotNull RecipeDto source) {

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach( category -> recipe.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
