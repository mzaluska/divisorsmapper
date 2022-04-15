package com.maza.divisorsmapper.controller;

import com.maza.divisorsmapper.controller.validator.ValidCategory;
import com.maza.divisorsmapper.dto.ErrorResponse;
import com.maza.divisorsmapper.dto.MappingCategoriesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Tag(name = "Categories api")
public interface CategoriesApi {

    @Operation(summary = "Get list of available categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success response with list of all available categories",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MappingCategoriesResponse.class))})
    })
    MappingCategoriesResponse getCategories();

    @Operation(summary = "Get mapping for given category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success response with list of all available categories",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Map.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid input supplied like wrong category", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    Map<Integer, String> getMapping(@PathVariable @ValidCategory @Parameter(name = "category", required = true, description = "Path parameter category.", example = "animals, furniture") String category);
}
