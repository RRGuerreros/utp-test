package com.utp.infrastructure.persistence.mapper;

import com.utp.domain.entity.Tag;
import com.utp.infrastructure.persistence.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagEntityMapper {
    Tag asTag(TagEntity tagEntity);

    TagEntity asTagEntity(Tag tag);

    String asString(TagEntity tagEntity);

    @Mapping(target = "id", ignore = true)
    Tag asTag(String name);

    List<Tag> asTags(List<String> names);
}
