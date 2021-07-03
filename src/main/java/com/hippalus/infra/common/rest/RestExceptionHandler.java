package com.hippalus.infra.common.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error("Exception has been occurred! Details: ", exception);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse("500", "Unknown error occurred"));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Object> handleRequestPropertyBindingError(WebExchangeBindException webExchangeBindException) {
        var errorMessage = webExchangeBindException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .map(error -> "required field" + error)
                .collect(Collectors.joining(" && "));

        log.trace("Exception has been occurred while request validation: {}", errorMessage);

        return ResponseEntity.badRequest().body(new ErrorResponse("400", errorMessage));
    }


    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException mismatchException) {
        log.trace("MethodArgumentTypeMismatchException occurred", mismatchException);
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse("422", mismatchException.getMessage()));
    }

    @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        log.trace("HttpMediaTypeNotSupportedException occurred", exception);
        return ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE).body(new ErrorResponse("415", exception.getMessage()));
    }

}
