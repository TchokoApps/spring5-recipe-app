package com.tchokoapps.springboot.spring5recipeapp.controllers;

import com.tchokoapps.springboot.spring5recipeapp.dto.IngredientDto;
import com.tchokoapps.springboot.spring5recipeapp.services.IngredientService;
import com.tchokoapps.springboot.spring5recipeapp.services.RecipeService;
import com.tchokoapps.springboot.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.info("listIngredients called");
        model.addAttribute("recipe", recipeService.findDtoById(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String udpateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.info("udpateRecipeIngredient called");
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientDto ingredientDto) {
        log.info("saveOrUpdate called");
        IngredientDto savedIngredientDto = ingredientService.saveIngredientDto(ingredientDto);
        log.info("Saved recipe id: " + savedIngredientDto.getRecipeId() + ", saved ingredient id: " + savedIngredientDto.getId());
        return "redirect:/recipe/" + savedIngredientDto.getRecipeId() + "/ingredient/" + savedIngredientDto.getId() + "/show";
    }
}
