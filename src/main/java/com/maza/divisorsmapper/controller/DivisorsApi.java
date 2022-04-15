package com.maza.divisorsmapper.controller;

import com.maza.divisorsmapper.controller.validator.ValidCategory;
import com.maza.divisorsmapper.dto.ErrorResponse;
import com.maza.divisorsmapper.dto.MapNumbersRequest;
import com.maza.divisorsmapper.dto.MapNumbersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Tag(name = "Divisors mapping api")
public interface DivisorsApi {
    @Operation(summary = "Get divisors by given category and for specified numbers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success response when divisors and mapping found",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = MapNumbersResponse.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid input supplied - wrong category or numbers from invalid range", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Mapping not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    MapNumbersResponse mapNumbers(
        @PathVariable @ValidCategory @Parameter(name = "category", required = true, description = "Path parameter category.", example = "animals, furniture") String category,
        @RequestBody @Valid @Parameter(name = "numbers", required = true, description = "Body parameter numbers list from range 1 to 20.") MapNumbersRequest numbers
    );

}
