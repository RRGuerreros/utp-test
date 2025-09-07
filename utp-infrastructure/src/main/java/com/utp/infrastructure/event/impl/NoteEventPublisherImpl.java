package com.utp.infrastructure.event.impl;

import com.utp.application.event.NoteEventPublisher;
import com.utp.domain.entity.Note;
import com.utp.infrastructure.event.model.NoteCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NoteEventPublisherImpl implements NoteEventPublisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publishNoteCreated(Note note) {
        publisher.publishEvent(new NoteCreatedEvent(note));
    }
}
