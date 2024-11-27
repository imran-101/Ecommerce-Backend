package com.userimran.mapper;

import com.userimran.dto.CategoryDto;
import com.userimran.entity.Category;
import org.modelmapper.ModelMapper;

public class CategoryMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Category convertToEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public static CategoryDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
