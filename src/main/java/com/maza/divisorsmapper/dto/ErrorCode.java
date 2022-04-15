package com.maza.divisorsmapper.dto;

import lombok.Getter;

public enum ErrorCode {
    INVALID_REQUEST("Invalid input"),
    INVALID_MAPPING_CATEGORY("Category is invalid. Please put one of expected categories listed in api/categories request"),
    INTERNAL_SERVER_ERROR("Some unexpected error occurred"),
    NO_MAPPING_FOR_CATEGORY("No mapping found for given category"),
    NO_MAPPING_FOR_NUMBER("No mappind found for given number");
    @Getter
    private final String message;

    ErrorCode(final String message) {
        this.message = message;
    }
}
