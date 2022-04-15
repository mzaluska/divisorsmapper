package com.maza.divisorsmapper.exception;

import com.maza.divisorsmapper.dto.ErrorCode;
import org.springframework.http.HttpStatus;

public class NoSuchMappingForCategoryException extends DivisorsApiException {

    public NoSuchMappingForCategoryException() {
        super(ErrorCode.NO_MAPPING_FOR_CATEGORY.getMessage(), ErrorCode.NO_MAPPING_FOR_CATEGORY, HttpStatus.NOT_FOUND);
    }
}
