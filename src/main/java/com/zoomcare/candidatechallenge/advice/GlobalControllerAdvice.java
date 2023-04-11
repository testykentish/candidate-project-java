package com.zoomcare.candidatechallenge.advice;

import com.zoomcare.candidatechallenge.exception.NotFoundException;
import com.zoomcare.candidatechallenge.util.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalControllerAdvice  {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNotFound(
            NotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
