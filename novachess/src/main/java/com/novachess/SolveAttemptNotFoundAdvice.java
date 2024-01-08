package com.novachess;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SolveAttemptNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SolveAttemptNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String solveAttemptNotFoundHandler(SolveAttemptNotFoundException ex) {
        return ex.getMessage();
    }
}
