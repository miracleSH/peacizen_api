package com.peacizen.peacizen_api.support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({BussineseException.class})
    private BussinessApiError handleBusinessInvalidException(BussineseException exception){
        return new BussinessApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now().toString(),
                exception.toString(), exception.getWarningMessage(), exception.getClass().getSimpleName(), exception.getReturnMap());
    }
}
