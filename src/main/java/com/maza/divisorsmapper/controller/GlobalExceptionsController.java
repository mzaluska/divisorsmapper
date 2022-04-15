package com.maza.divisorsmapper.controller;

import com.maza.divisorsmapper.dto.ErrorCode;
import com.maza.divisorsmapper.dto.ErrorResponse;
import com.maza.divisorsmapper.exception.DivisorsApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionsController {

    @ExceptionHandler(value = DivisorsApiException.class)
    public ResponseEntity<ErrorResponse> handleDivisorsApiException(final DivisorsApiException ex) {
        log.error("handleDivisorsApiException: ", ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(handleExceptionInternal(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentException(final MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentException: ", ex);
        final String message;
        if (ex.getFieldError() != null) {
            message = ex.getFieldError().getDefaultMessage();
        } else {
            message = ErrorCode.INVALID_REQUEST.getMessage();
        }
        return ResponseEntity.badRequest().body(handleExceptionInternal(ErrorCode.INVALID_REQUEST, message));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex) {
        final List<String> errors = new ArrayList<>();
        for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage() + " " + violation.getInvalidValue());
        }
        final String message = String.join("", errors);
        return ResponseEntity.badRequest().body(handleExceptionInternal(ErrorCode.INVALID_REQUEST, message));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleCheckedException(final Exception ex) {
        log.error("handleCheckedException: ", ex);
        final String message = ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
        return ResponseEntity.internalServerError().body(handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR, message));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleUncheckedException(final RuntimeException ex) {
        log.error("handleUncheckedException: ", ex);
        final String message = ErrorCode.INTERNAL_SERVER_ERROR.getMessage();
        return ResponseEntity.internalServerError().body(handleExceptionInternal(ErrorCode.INTERNAL_SERVER_ERROR, message));
    }

    private ErrorResponse handleExceptionInternal(final ErrorCode errorCode, final String message) {
        return ErrorResponse.builder()
            .code(errorCode)
            .message(message)
            .build();
    }
}
