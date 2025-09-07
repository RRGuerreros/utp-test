package com.utp.infrastructure.repository;

import com.utp.domain.entity.Note;
import com.utp.domain.repository.NoteRepository;
import com.utp.infrastructure.persistence.mapper.NoteEntityMapper;
import com.utp.infrastructure.persistence.repository.NoteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepositoryJpaAdapter implements NoteRepository {

    private final NoteJpaRepository noteJpaRepository;
    private final NoteEntityMapper noteEntityMapper;

    public NoteRepositoryJpaAdapter(NoteJpaRepository noteJpaRepository, NoteEntityMapper noteEntityMapper) {
        this.noteJpaRepository = noteJpaRepository;
        this.noteEntityMapper = noteEntityMapper;
    }

    @Override
    public Note save(Note entity) {
        return noteEntityMapper.asNote(
                noteJpaRepository.save(noteEntityMapper.asNoteEntity(entity)));
    }

    @Override
    public List<Note> findAllByUserUsername(String username) {
        return noteEntityMapper.asNotes(noteJpaRepository.findAllByUserUsername(username));
    }
}
