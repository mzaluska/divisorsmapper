package com.maza.divisorsmapper.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
@Schema
public class MappingCategoriesResponse {
    List<String> categories;
}
