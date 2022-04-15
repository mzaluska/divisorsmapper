package com.maza.divisorsmapper.exception;

import com.maza.divisorsmapper.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class NoSuchMappingForNumberException extends DivisorsApiException {

    public NoSuchMappingForNumberException() {
        super(ErrorCode.NO_MAPPING_FOR_NUMBER.getMessage(), ErrorCode.NO_MAPPING_FOR_NUMBER, HttpStatus.NOT_FOUND);
    }
}
