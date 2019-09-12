package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.NotesDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class NotesToNotesDto implements Converter<Notes, NotesDto> {

    @Synchronized
    @Nullable
    @Override
    public NotesDto convert(@NotNull Notes notes) {
        final NotesDto notesDto = new NotesDto();
        notesDto.setId(notes.getId());
        notesDto.setRecipeNotes(notes.getRecipeNotes());
        return notesDto;
    }
}
