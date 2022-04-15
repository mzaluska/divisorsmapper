package com.maza.divisorsmapper.controller.validator;

import com.maza.divisorsmapper.model.Category;
import com.maza.divisorsmapper.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class MappingCategoryValidator implements ConstraintValidator<ValidCategory, String> {

    private final CategoryRepository categoryRepository;

    public MappingCategoryValidator(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(final ValidCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        log.debug("Validating mapping category value..");
        final List<Category> categories = categoryRepository.findAll();
        return !value.isBlank() && categories.stream().anyMatch(category -> category.getName().equals(value));
    }

}
