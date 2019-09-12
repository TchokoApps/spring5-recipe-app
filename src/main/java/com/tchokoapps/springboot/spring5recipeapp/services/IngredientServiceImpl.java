package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.converters.IngredientDtoToIngredient;
import com.tchokoapps.springboot.spring5recipeapp.converters.IngredientToIngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import com.tchokoapps.springboot.spring5recipeapp.repositories.RecipeRepository;
import com.tchokoapps.springboot.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientDto ingredientToIngredientDto,
                                 IngredientDtoToIngredient ingredientDtoToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.ingredientDtoToIngredient = ingredientDtoToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Recipe recipe = findRecipeById(recipeId);
        return getIngredientDto(ingredientId, recipe);
    }

    @Override
    @Transactional
    public IngredientDto saveIngredientDto(IngredientDto ingredientDto) {
        return null;
    }

    private IngredientDto getIngredientDto(Long ingredientId, Recipe recipe) {
        Optional<IngredientDto> ingredientDtoOpt = recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).map(ingredient -> ingredientToIngredientDto.convert(ingredient)).findFirst();
        if (!ingredientDtoOpt.isPresent()) {
            throw new RuntimeException("ingredientDto cannot be found");
        }
        return ingredientDtoOpt.get();
    }

    private Recipe findRecipeById(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe object doesn´t exist in the database");
        }

        return recipeOptional.get();
    }
}
