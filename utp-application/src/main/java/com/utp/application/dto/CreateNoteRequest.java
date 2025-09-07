package com.utp.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNoteRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private List<String> tags;
}
