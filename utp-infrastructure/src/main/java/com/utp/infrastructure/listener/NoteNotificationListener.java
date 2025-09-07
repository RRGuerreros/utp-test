package com.utp.infrastructure.listener;

import com.utp.infrastructure.event.model.NoteCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoteNotificationListener {

    @EventListener
    public void onNoteCreated(NoteCreatedEvent event) {
        log.info("Realizar alguna otra operación posterior al crear la nota");
    }
}
