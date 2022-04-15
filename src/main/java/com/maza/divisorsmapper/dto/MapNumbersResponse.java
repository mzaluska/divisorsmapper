package com.maza.divisorsmapper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Builder
@Value
@Schema
public class MapNumbersResponse {
    Map<Integer, List<String>> response;
}
