package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.converters.IngredientDtoToIngredient;
import com.tchokoapps.springboot.spring5recipeapp.converters.IngredientToIngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Ingredient;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import com.tchokoapps.springboot.spring5recipeapp.entities.UnitOfMeasure;
import com.tchokoapps.springboot.spring5recipeapp.repositories.RecipeRepository;
import com.tchokoapps.springboot.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

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
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDto.getRecipeId());

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Object not FOUND");
        }

        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDto.getId()))
                .findFirst();

        if(!ingredientOptional.isPresent()) {
            recipe.addIngredient(ingredientDtoToIngredient.convert(ingredientDto));
        } else {
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setDescription(ingredientDto.getDescription());
            ingredient.setAmount(ingredientDto.getAmount());

            Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findById(ingredientDto.getUom().getId());

            if(unitOfMeasureOptional.isPresent()) {
                ingredient.setUom(unitOfMeasureOptional.get());
            } else {
                throw new RuntimeException("UOM cannot be found");
            }
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Optional<Ingredient> ingredientOptional2 = savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDto.getId()))
                .findFirst();

        if(ingredientOptional2.isPresent()) {
            return ingredientToIngredientDto.convert(ingredientOptional2.get());
        } else {
            throw new RuntimeException("Ingredient cannot be found");
        }
    }

    private IngredientDto getIngredientDto(Long ingredientId, Recipe recipe) {
        Optional<IngredientDto> ingredientDtoOpt = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientDto.convert(ingredient)).findFirst();
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
