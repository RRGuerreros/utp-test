package com.utp.application.usecase.note;

import com.utp.application.dto.CreateNoteRequest;
import com.utp.application.dto.NoteResponse;

public interface CreateNoteUseCase {
    NoteResponse execute(CreateNoteRequest createNoteRequest);
}
