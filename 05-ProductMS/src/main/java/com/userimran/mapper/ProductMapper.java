package com.userimran.mapper;

import com.userimran.dto.ProductDto;
import com.userimran.entity.Product;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Product convertToEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    public static ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
