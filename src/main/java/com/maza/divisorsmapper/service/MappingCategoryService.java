package com.maza.divisorsmapper.service;

import com.maza.divisorsmapper.dto.MappingCategoriesResponse;

import java.util.Map;

public interface MappingCategoryService {

    MappingCategoriesResponse getMappingCategories();

    Map<Integer, String> getMappingForCategory(String name);
}
