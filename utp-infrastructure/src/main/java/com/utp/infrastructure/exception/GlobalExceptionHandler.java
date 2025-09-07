package com.utp.infrastructure.exception;

import com.utp.domain.exception.EntityNotFoundException;
import com.utp.domain.exception.BusinessException;
import com.utp.infrastructure.dto.ExceptionApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionApiResponse> handleBusinessException(BusinessException e){
        ExceptionApiResponse exceptionApiResponse = new ExceptionApiResponse();
        exceptionApiResponse.setMessage(e.getMessage());
        exceptionApiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(exceptionApiResponse.getStatus()).body(exceptionApiResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionApiResponse> handleEntityNotFoundException(EntityNotFoundException e){
        ExceptionApiResponse exceptionApiResponse = new ExceptionApiResponse();
        exceptionApiResponse.setMessage(e.getMessage());
        exceptionApiResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(exceptionApiResponse.getStatus()).body(exceptionApiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ExceptionApiResponse exceptionApiResponse = new ExceptionApiResponse();
        exceptionApiResponse.setMessage("Error de validación");
        exceptionApiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        List<Map<String, String>> errors = ex.getFieldErrors()
                .stream()
                .map(error ->
                    Map.of("field", error.getField(), "message", Objects.requireNonNullElse(error.getDefaultMessage(), "Error de campo"))
                ).toList();
        exceptionApiResponse.setErrors(errors);
        return ResponseEntity.status(exceptionApiResponse.getStatus()).body(exceptionApiResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionApiResponse> handleAccessDenied(AccessDeniedException ex) {
        ExceptionApiResponse exceptionApiResponse = new ExceptionApiResponse();
        exceptionApiResponse.setMessage("Acceso denegado");
        exceptionApiResponse.setStatus(HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(exceptionApiResponse.getStatus()).body(exceptionApiResponse);
    }
}
