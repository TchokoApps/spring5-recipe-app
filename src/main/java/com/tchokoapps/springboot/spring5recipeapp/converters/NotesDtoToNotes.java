package com.tchokoapps.springboot.spring5recipeapp.converters;

import com.tchokoapps.springboot.spring5recipeapp.dto.NotesDto;
import com.tchokoapps.springboot.spring5recipeapp.entities.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class NotesDtoToNotes implements Converter<NotesDto, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(@NotNull NotesDto notesDto) {
        final Notes notes = new Notes();
        notes.setId(notesDto.getId());
        notes.setRecipeNotes(notesDto.getRecipeNotes());
        return notes;
    }
}
