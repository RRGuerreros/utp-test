package com.utp.infrastructure.event.model;

import com.utp.domain.entity.Note;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoteCreatedEvent {
    private final Note note;
}