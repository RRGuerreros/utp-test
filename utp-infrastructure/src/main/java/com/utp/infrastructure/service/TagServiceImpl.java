package com.utp.infrastructure.service;

import com.utp.application.services.TagService;
import com.utp.domain.entity.Tag;
import com.utp.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> saveAll(List<String> tags) {
        return tags.stream().map(tagRepository::findOrCreate).toList();
    }
}
