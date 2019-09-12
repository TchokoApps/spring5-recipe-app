package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.CategoryDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDto> {

    @Override
    @Nullable
    @Synchronized
    public CategoryDto convert(@NotNull Category category) {
        final CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setDescription((category.getDescription()));
        return categoryDto;
    }
}
