package com.utp.infrastructure.facade;

import com.utp.application.dto.CreateNoteRequest;
import com.utp.application.dto.NoteResponse;
import com.utp.application.usecase.note.CreateNoteUseCase;
import com.utp.application.usecase.note.ListNoteByUserAuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteFacade {

    private final CreateNoteUseCase createNoteUseCase;
    private final ListNoteByUserAuthUseCase listNoteByUserAuthUseCase;

    @Transactional
    public NoteResponse createNote(CreateNoteRequest createNoteRequest){
        return createNoteUseCase.execute(createNoteRequest);
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> listNotesByUsernameAuth(String username){
        return listNoteByUserAuthUseCase.execute(username);
    }
}
