package com.maza.divisorsmapper.controller;

import com.maza.divisorsmapper.controller.validator.ValidCategory;
import com.maza.divisorsmapper.dto.MapNumbersRequest;
import com.maza.divisorsmapper.dto.MapNumbersResponse;
import com.maza.divisorsmapper.service.DivisorsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/divisors/{category}")
@Validated
public class DivisorsController implements DivisorsApi {

    private final DivisorsService divisorsService;

    public DivisorsController(final DivisorsService divisorsService) {
        this.divisorsService = divisorsService;
    }

    @Override
    @GetMapping("/")
    public MapNumbersResponse mapNumbers(@PathVariable @ValidCategory final String category,
                                         @RequestBody @Valid final MapNumbersRequest numbers) {
        return divisorsService.mapNumbers(category, numbers.getNumbers());
    }

}
