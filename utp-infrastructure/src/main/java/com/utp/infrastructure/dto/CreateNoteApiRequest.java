package com.utp.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateNoteApiRequest implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private final String title;
    @NotBlank
    private final String content;
    @NotNull(message = "Debe incluir al menos un tag")
    @Size(min = 1, max = 5, message = "Debe tener entre 1 y 5 tags")
    private final List<String> tags;
}
