package com.tchokoapps.springboot.spring5recipeapp.repositories;

import com.tchokoapps.springboot.spring5recipeapp.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
