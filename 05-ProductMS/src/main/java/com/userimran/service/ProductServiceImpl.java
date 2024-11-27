package com.userimran.service;

import com.userimran.constants.AppConstants;
import com.userimran.dto.ProductDto;
import com.userimran.entity.Category;
import com.userimran.entity.Product;
import com.userimran.exception.ProductServiceException;
import com.userimran.mapper.CategoryMapper;
import com.userimran.mapper.ProductMapper;
import com.userimran.repository.CategoryRepository;
import com.userimran.repository.ProductRepository;
import com.userimran.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto, MultipartFile file) throws Exception {

        Product productByName = productRepository.findByProductName(productDto.getProductName());

        if (productByName == null) {
            Product product = ProductMapper.convertToEntity(productDto);
            if (file != null) {
                String fileName = FileUtils.saveFile(file);
                product.setProductImageUrl(fileName);
            }
            String categoryName = productDto.getCategoryDto().getCategoryName();
            Integer categoryId = productDto.getCategoryDto().getCategoryId();
            Category existCategory = categoryRepository.findByCategoryName(categoryName);
//            categoryRepository.findById(categoryId).ifPresent(category -> {});
            if (existCategory == null) {
                throw new ProductServiceException(AppConstants.CATEGORY_NOT_FOUND, AppConstants.CATEGORY_NOT_FOUND_ERR_CD);
            }
            product.setCategory(existCategory);
            Product savedProduct = productRepository.save(product);
            return ProductMapper.convertToDto(savedProduct);
        }
        return null;
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile file) throws Exception {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        if (file != null) {
            String fileName = FileUtils.saveFile(file);
            existingProduct.setProductImageUrl(fileName);
        }
        existingProduct.setDiscount(productDto.getDiscount());
        existingProduct.setPriceBeforeDiscount(productDto.getPriceBeforeDiscount());
        existingProduct.setCategory(CategoryMapper.convertToEntity(productDto.getCategoryDto()));
        return ProductMapper.convertToDto(productRepository.save(existingProduct));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
        ProductDto productDto = ProductMapper.convertToDto(product);
//        productDto.setCategoryDto(categoryRepository.findAll());
        Category categoryById = categoryRepository.findById(product.getCategory().getCategoryId()).get();
        ProductDto productDto1 = ProductMapper.convertToDto(product);
        productDto1.setCategoryDto(CategoryMapper.convertToDto(categoryById));
        return productDto1;
//        return ProductMapper.convertToDto(product);
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findProductByCategoryId(categoryId)
                .stream().map(ProductMapper::convertToDto).toList();
    }

    @Override
    public ProductDto deleteProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
        productRepository.delete(product);
        return ProductMapper.convertToDto(product);
    }

    @Override
    public boolean updateStock(Integer productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND, AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
        product.setStock(quantity);
        productRepository.save(product);
        return true;
    }
}
