package com.userimran.service;

import com.userimran.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto, MultipartFile file) throws Exception;

    ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile file) throws Exception;

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Integer productId);

    List<ProductDto> getProductsByCategoryId(Integer categoryId);

    ProductDto deleteProductById(Integer productId);

    boolean updateStock(Integer productId, Integer quantity);
}
