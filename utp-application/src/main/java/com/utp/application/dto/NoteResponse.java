package com.utp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private UserResponse user;
    private String title;
    private String content;
    private List<TagResponse> tags;
}
