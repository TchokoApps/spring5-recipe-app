package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.converters.RecipeDtoToRecipe;
import com.tchokoapps.springboot.spring5recipeapp.converters.RecipeToRecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.dto.RecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.repositories.RecipeRepository;
import com.tchokoapps.springboot.spring5recipeapp.entities.Recipe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDtoToRecipe recipeDtoToRecipe;
    private final RecipeToRecipeDto recipeToRecipeDto;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDtoToRecipe recipeDtoToRecipe, RecipeToRecipeDto recipeToRecipeDto) {
        this.recipeRepository = recipeRepository;
        this.recipeDtoToRecipe = recipeDtoToRecipe;
        this.recipeToRecipeDto = recipeToRecipeDto;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("getRecipes has been called");
        HashSet<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent())
            throw new RuntimeException("Recipe not found");
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeDto saveRecipeDto(RecipeDto recipeDto) {
        Recipe convertedRecipe = recipeDtoToRecipe.convert(recipeDto);
        Recipe savedRecipe = recipeRepository.save(convertedRecipe);
        log.info("Saved recipe id: " + savedRecipe.getId());
        return recipeToRecipeDto.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeDto findDtoById(Long id) {
        return recipeToRecipeDto.convert(findById(id));
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
