package com.utp.application.event;

import com.utp.domain.entity.Note;

public interface NoteEventPublisher {
    void publishNoteCreated(Note note);
}
