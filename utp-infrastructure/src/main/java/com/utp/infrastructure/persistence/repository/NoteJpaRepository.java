package com.utp.infrastructure.persistence.repository;

import com.utp.infrastructure.persistence.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteJpaRepository extends JpaRepository<NoteEntity, Integer> {

    List<NoteEntity> findAllByUserUsername(String username);
}
