package com.utp.application.usecase.note;

import com.utp.application.dto.NoteResponse;

import java.util.List;

public interface ListNoteByUserAuthUseCase {
    List<NoteResponse> execute(String username);
}
