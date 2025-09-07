package com.utp.infrastructure.persistence.mapper;

import com.utp.domain.entity.Note;
import com.utp.infrastructure.persistence.entity.NoteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TagEntityMapper.class})
public interface NoteEntityMapper {
    NoteEntity asNoteEntity(Note note);
    Note asNote(NoteEntity noteEntity);
    List<Note> asNotes(List<NoteEntity> noteEntities);
}
