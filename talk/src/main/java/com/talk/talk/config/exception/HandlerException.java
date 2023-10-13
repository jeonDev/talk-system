package com.talk.talk.config.exception;

import com.talk.talk.config.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandlerException {

    /**
     * Custom Exception
     * */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiExceptionHandler(final ApiException e) {
        log.error("ApiException : {} : {}"
                , e.getCode()
                , e.getMessage()
                , e
        );

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @Valid Exception
     * */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}"
                , e.getMessage()
                , e
        );
        String failValidMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getStatusCode().toString())
                .message(failValidMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Etc Exception
     * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(final Exception e) {
        log.error("Exception : {}"
                , e.getMessage()
                , e
        );

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getMessage())
                .message(ExceptionEnum.getMessage(e.getMessage()).getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
