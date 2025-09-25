package ru.itmo.jpa.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itmo.jpa.dto.ErrorDto;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleRuntime(MethodArgumentNotValidException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RuntimeException.class)
    public ErrorDto handleRuntime2(RuntimeException ex) {
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleRuntime3(Exception ex) {
        return new ErrorDto(ex.getMessage());
    }
}
