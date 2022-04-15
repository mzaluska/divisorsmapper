package com.maza.divisorsmapper.service.impl;

import com.maza.divisorsmapper.dto.MappingCategoriesResponse;
import com.maza.divisorsmapper.model.Category;
import com.maza.divisorsmapper.model.Mapping;
import com.maza.divisorsmapper.repository.CategoryRepository;
import com.maza.divisorsmapper.repository.MappingRepository;
import com.maza.divisorsmapper.service.MappingCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MappingCategoryServiceImpl implements MappingCategoryService {

    private final CategoryRepository categoryRepository;
    private final MappingRepository mappingRepository;

    public MappingCategoryServiceImpl(final CategoryRepository categoryRepository, final MappingRepository mappingRepository) {
        this.categoryRepository = categoryRepository;
        this.mappingRepository = mappingRepository;
    }

    @Override
    public MappingCategoriesResponse getMappingCategories() {
        final List<Category> entities = categoryRepository.findAll();
        final List<String> categories = entities.stream().map(Category::getName).collect(Collectors.toList());
        return MappingCategoriesResponse.builder().categories(categories).build();
    }

    @Override
    public Map<Integer, String> getMappingForCategory(final String name) {
        final List<Mapping> allByCategoryName = mappingRepository.findAllByCategoryName(name);
        return allByCategoryName.stream().collect(Collectors.toMap(Mapping::getNumber, Mapping::getWord));
    }
}
