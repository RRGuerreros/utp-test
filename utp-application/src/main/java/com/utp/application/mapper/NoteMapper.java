package com.utp.application.mapper;

import com.utp.application.dto.CreateNoteRequest;
import com.utp.application.dto.NoteResponse;
import com.utp.domain.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {TagMapper.class})
public interface NoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Note asNote(CreateNoteRequest createNoteRequest);

    NoteResponse asNoteResponse(Note note);

    List<NoteResponse> asNotesResponse(List<Note> notes);
}
