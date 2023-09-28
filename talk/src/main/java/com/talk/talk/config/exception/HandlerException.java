package com.talk.talk.config.exception;

import com.talk.talk.config.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandlerException {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiExceptionHandler(final ApiException e) {
        log.error("ApiException : {} : {}"
                , e.getCode()
                , e.getMessage()
        );

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> apiExceptionHandler(final Exception e) {
        log.error("Exception : {}"
                , e.getMessage()
        );

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getMessage())
                .message(ExceptionEnum.getMessage(e.getMessage()).getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
