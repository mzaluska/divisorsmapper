package com.maza.divisorsmapper.exception;

import com.maza.divisorsmapper.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidMappingCategoryException extends DivisorsApiException {

    public InvalidMappingCategoryException() {
        super(ErrorCode.INVALID_MAPPING_CATEGORY.getMessage(), ErrorCode.INVALID_MAPPING_CATEGORY, HttpStatus.NOT_FOUND);
    }
}
