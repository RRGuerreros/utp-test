package com.utp.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utp.domain.enums.Role;
import lombok.*;

@Getter
@Setter
public class User {

    private final Integer id;
    private final String username;
    private final String password;
    private final Role role;

    @JsonCreator
    public User(
            @JsonProperty("id") Integer id,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("role") Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
