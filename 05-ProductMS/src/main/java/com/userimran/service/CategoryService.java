package com.userimran.service;

import com.userimran.dto.CategoryDto;
import com.userimran.dto.ProductDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Integer categoryId ,CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer categoryId);
    CategoryDto deleteCategoryById(Integer categoryId);

}
