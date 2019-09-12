package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.UnitOfMeasureDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(@NotNull UnitOfMeasureDto unitOfMeasureDto) {
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(unitOfMeasureDto.getId());
        uom.setDescription(unitOfMeasureDto.getDescription());
        return uom;
    }
}
