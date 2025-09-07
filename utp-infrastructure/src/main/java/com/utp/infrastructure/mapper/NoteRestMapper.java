package com.utp.infrastructure.mapper;

import com.utp.application.dto.CreateNoteRequest;
import com.utp.infrastructure.dto.CreateNoteApiRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteRestMapper {

    CreateNoteRequest asCreateNoteRequest(CreateNoteApiRequest createNoteApiRequest);
}
