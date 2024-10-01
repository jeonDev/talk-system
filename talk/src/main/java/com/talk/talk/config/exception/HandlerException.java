package com.talk.talk.config.exception;

import com.talk.talk.config.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class HandlerException {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceExceptionHandler(final ServiceException e) {
        log.error("ServiceException : {} : {}", e.getCode(), e.getMessage(), e);

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage(), e);
        String failValidMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getStatusCode().toString())
                .message(failValidMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> dataIntegrityViolationException(final DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException : {}", e.getMessage(), e);

        ApiResponse<?> response = ApiResponse.builder()
                .status(ErrorType.DATA_DUPLICATE.getCode())
                .message(ErrorType.DATA_DUPLICATE.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(final Exception e) {
        log.error("Exception : {}", e.getMessage(), e);

        ApiResponse<?> response = ApiResponse.builder()
                .status(e.getMessage())
                .message(ErrorType.getMessage(e.getMessage()).getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
