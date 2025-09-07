package com.utp.infrastructure.persistence.repository;

import com.utp.infrastructure.persistence.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagJpaRepository extends JpaRepository<TagEntity, Integer> {
    Optional<TagEntity> findByName(String name);
}
