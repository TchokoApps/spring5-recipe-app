package com.tchokoapps.springboot.spring5recipeapp.repositories;

import com.tchokoapps.springboot.spring5recipeapp.entities.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
