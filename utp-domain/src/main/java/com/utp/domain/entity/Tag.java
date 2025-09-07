package com.utp.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Tag {
    private final Integer id;
    private final String name;

    @JsonCreator
    public Tag(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        return Objects.equals(name, ((Tag) o).name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
