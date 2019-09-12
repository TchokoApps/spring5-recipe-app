package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.CategoryDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category> {

    @Nullable
    @Synchronized
    @Override
    public Category convert(@NotNull CategoryDto categoryDto) {
        final Category category = new Category();
        category.setId(categoryDto.getId());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
