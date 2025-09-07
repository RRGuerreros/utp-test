package com.utp.application.services;

import com.utp.domain.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> saveAll(List<String> tags);
}
