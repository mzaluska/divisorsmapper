package com.maza.divisorsmapper.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ErrorResponse {
    ErrorCode code;
    String message;
}
