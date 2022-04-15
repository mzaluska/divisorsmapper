package com.maza.divisorsmapper.exception;

import com.maza.divisorsmapper.dto.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DivisorsApiException extends RuntimeException {
    protected final String message;
    protected final ErrorCode code;
    protected final HttpStatus httpStatus;

    public DivisorsApiException(final String message, final ErrorCode code, final HttpStatus httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
