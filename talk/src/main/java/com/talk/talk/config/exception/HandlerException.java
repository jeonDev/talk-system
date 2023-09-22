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
        return new ResponseEntity<>(new ApiResponse<>(e), HttpStatus.OK);
    }

}
