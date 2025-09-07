package com.utp.infrastructure.repository;

import com.utp.domain.entity.Tag;
import com.utp.domain.repository.TagRepository;
import com.utp.infrastructure.persistence.mapper.TagEntityMapper;
import com.utp.infrastructure.persistence.repository.TagJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryJpaAdapter implements TagRepository {

    private final TagJpaRepository tagJpaRepository;
    private final TagEntityMapper tagEntityMapper;

    public TagRepositoryJpaAdapter(TagJpaRepository tagJpaRepository, TagEntityMapper tagEntityMapper) {
        this.tagJpaRepository = tagJpaRepository;
        this.tagEntityMapper = tagEntityMapper;
    }

    @Override
    public Tag findOrCreate(String name) {
        return tagJpaRepository.findByName(name)
                .map(tagEntityMapper::asTag)
                .orElseGet(() -> save(tagEntityMapper.asTag(name)));
    }

    @Override
    public Tag save(Tag entity) {
        return tagEntityMapper.asTag(
                tagJpaRepository.save(tagEntityMapper.asTagEntity(entity))
        );
    }
}
