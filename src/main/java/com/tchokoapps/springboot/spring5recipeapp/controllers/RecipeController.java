package com.tchokoapps.springboot.spring5recipeapp.controllers;

import com.tchokoapps.springboot.spring5recipeapp.dto.RecipeDto;
import com.tchokoapps.springboot.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        log.info("showById called");
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));
        return "recipe/show";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        log.info("newRecipe called");
        model.addAttribute("recipe", new RecipeDto());
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDto recipeDto) {
        log.info("saveOrUpdate called");
        RecipeDto savedRecipeDto = recipeService.saveRecipeDto(recipeDto);
        return "redirect:/recipe/" + savedRecipeDto.getId() + "/show";
    }

    @GetMapping("recipe/{id}/update")
    public String udpateRecipe(@PathVariable String id, Model model) {
        log.info("udpateRecipe called");
        model.addAttribute("recipe", recipeService.findDtoById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model) {
        log.info("deleteRecipe called");
        log.info("Recipe id to delete: " + id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";

    }
}
