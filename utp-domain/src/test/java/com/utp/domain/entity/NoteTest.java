package com.utp.domain.entity;

import com.utp.domain.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {

    @Test
    void shouldCreateNoteWithValidTags() {
        List<Tag> tags = List.of(new Tag(null,"Java"), new Tag(null,"Spring"));
        Note note = new Note(1L, "Programacion", "programando en java y spring", tags, null);

        assertEquals(2, note.getTags().size());
        assertEquals("Java", note.getTags().get(0).getName());
        assertEquals("Spring", note.getTags().get(1).getName());
    }

    @Test
    void shouldThrowBusinessExceptionWhenTagsAreNull() {
        BusinessException ex = assertThrows(BusinessException.class,
                () -> new Note(1L, "Programacion", "programando en java y spring", null, null));
        assertEquals("Debe incluir al menos un tag", ex.getMessage());
    }

    // aqui se continuaría con los otros test que se requiera...
}
