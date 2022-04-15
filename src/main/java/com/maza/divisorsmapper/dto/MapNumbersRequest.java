package com.maza.divisorsmapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MapNumbersRequest {

    @NotEmpty(message = "Numbers list should not be empty")
    private List<
        @Min(value = 1, message = "Number should not be less than 1")
        @Max(value = 20, message = "Number should not be greater than 1")
            Integer> numbers;

}
