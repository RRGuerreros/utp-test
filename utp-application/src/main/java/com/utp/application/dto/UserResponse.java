package com.utp.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utp.domain.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private Role role;
}
