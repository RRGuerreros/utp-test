package com.utp.application.usecase.note;

import com.utp.application.annotation.UseCase;
import com.utp.application.dto.NoteResponse;
import com.utp.application.mapper.NoteMapper;
import com.utp.domain.repository.NoteRepository;

import java.util.List;

@UseCase
public class ListNoteByUserAuthUseCaseImpl implements ListNoteByUserAuthUseCase {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public ListNoteByUserAuthUseCaseImpl(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Override
    public List<NoteResponse> execute(String username) {
        return noteMapper.asNotesResponse(noteRepository.findAllByUserUsername(username));
    }
}
