package com.utp.domain.repository;

import com.utp.domain.entity.Note;

import java.util.List;

public interface NoteRepository extends SaveRepository<Note>{

    List<Note> findAllByUserUsername(String username);
}
