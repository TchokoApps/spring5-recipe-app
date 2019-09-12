package com.tchokoapps.springboot.spring5recipeapp.controllers;

import com.tchokoapps.springboot.spring5recipeapp.repositories.CategoryRepository;
import com.tchokoapps.springboot.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.tchokoapps.springboot.spring5recipeapp.entities.Category;
import com.tchokoapps.springboot.spring5recipeapp.entities.UnitOfMeasure;
import com.tchokoapps.springboot.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.info("getIndexPage has been called");
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        log.info("Cat Id is: " + categoryOptional.get().getId());
        log.info("UOM ID is: " + unitOfMeasureOptional.get().getId());

        model.addAttribute("recipes", recipeService.getRecipes());

        return "Index";
    }
}
