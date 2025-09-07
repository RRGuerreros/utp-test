package com.utp.application.mapper;

import com.utp.domain.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TagMapper {

    @Mapping(target = "id", ignore = true)
    Tag asTag(String name);

    List<Tag> asTag(List<String> names);
}
