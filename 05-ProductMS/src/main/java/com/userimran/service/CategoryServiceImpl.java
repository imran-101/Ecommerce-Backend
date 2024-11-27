package com.userimran.service;

import com.userimran.constants.AppConstants;
import com.userimran.dto.CategoryDto;
import com.userimran.entity.Category;
import com.userimran.exception.ProductServiceException;
import com.userimran.mapper.CategoryMapper;
import com.userimran.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.convertToEntity(categoryDto);
        Category existCategoryName = categoryRepository.findByCategoryName(category.getCategoryName());
        if (existCategoryName == null) {
            return CategoryMapper.convertToDto(categoryRepository.save(category));
        }
        return null;
    }

    @Override
    public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
        existingCategory.setCategoryName(categoryDto.getCategoryName());
        return CategoryMapper.convertToDto(categoryRepository.save(existingCategory));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(CategoryMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
        return CategoryMapper.convertToDto(category);
    }

    @Override
    public CategoryDto deleteCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD));
        categoryRepository.delete(category);
        return CategoryMapper.convertToDto(category);
    }
}
