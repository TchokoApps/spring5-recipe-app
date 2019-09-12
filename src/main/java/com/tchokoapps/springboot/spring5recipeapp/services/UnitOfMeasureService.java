package com.tchokoapps.springboot.spring5recipeapp.services;

import com.tchokoapps.springboot.spring5recipeapp.dto.UnitOfMeasureDto;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureDto> listAllUoms();
}
