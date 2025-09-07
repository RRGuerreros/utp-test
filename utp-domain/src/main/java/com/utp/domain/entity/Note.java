package com.utp.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utp.domain.exception.BusinessException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Note {

    private final Long id;
    private String title;
    private String content;
    @Setter(AccessLevel.NONE)
    private List<Tag> tags;
    private User user;

    @JsonCreator
    public Note(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("tags") List<Tag> tags,
            @JsonProperty("user") User user) {
        this.validateTags(tags);
        this.id = id;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.user = user;
    }

    public void setTags(List<Tag> tags) {
        this.validateTags(tags);
        this.tags = new ArrayList<>(tags);
    }

    public void validateTags(List<Tag> tags){
        if (tags == null || tags.isEmpty()) {
            throw new BusinessException("Debe incluir al menos un tag");
        }
        if (tags.size() > 5) {
            throw new BusinessException("No puede incluir más de 5 tags");
        }
        if(tags.stream().distinct().count() != tags.size()){
            throw new BusinessException("Los tags no pueden repetirse");
        }
    }
}
