package com.utp.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionApiResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private int status;
    private List<Map<String, String>> errors;
}
