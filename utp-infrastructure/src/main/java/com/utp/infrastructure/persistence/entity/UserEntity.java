package com.utp.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utp.domain.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String username;
    @JsonIgnore
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
