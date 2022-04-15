package com.maza.divisorsmapper.controller;

import com.maza.divisorsmapper.controller.validator.ValidCategory;
import com.maza.divisorsmapper.dto.MappingCategoriesResponse;
import com.maza.divisorsmapper.service.MappingCategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/categories")
@Validated
public class CategoriesController implements CategoriesApi {

    private final MappingCategoryService service;

    public CategoriesController(final MappingCategoryService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/")
    public MappingCategoriesResponse getCategories() {
        return service.getMappingCategories();
    }

    @Override
    @GetMapping("/{category}")
    public Map<Integer, String> getMapping(@PathVariable @ValidCategory final String category) {
        return service.getMappingForCategory(category);
    }

}
