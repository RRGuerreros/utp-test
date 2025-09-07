package com.utp.domain.repository;

import com.utp.domain.entity.Tag;

public interface TagRepository  extends SaveRepository<Tag>{

    Tag findOrCreate(String name);
}
