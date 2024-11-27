package com.userimran.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userimran.dto.ProductDto;
import com.userimran.props.AppProps;
import com.userimran.response.ApiResponse;
import com.userimran.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/api")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;
    private final AppProps props;

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestParam("product") String productJson, @RequestParam(name = "file", required = false) MultipartFile file) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ProductDto productDto = mapper.readValue(productJson, ProductDto.class);

        Map<String, String> messages = props.getMessages();
        ApiResponse<ProductDto> response = new ApiResponse<>();

        ProductDto addedProduct = productService.addProduct(productDto, file);

        if (addedProduct != null) {
            response.setStatusCode(201);
            response.setMessage(messages.get("productAdded"));
            response.setData(addedProduct);
        } else {
            response.setMessage(messages.get("productAddErr"));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto, @RequestParam(name = "file", required = false) MultipartFile file) throws Exception {

        Map<String, String> messages = props.getMessages();
        ApiResponse<ProductDto> response = new ApiResponse<>();

        ProductDto updatedProduct = productService.updateProduct(id, productDto, file);

        if (updatedProduct != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("productUpdate"));
            response.setData(updatedProduct);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("productUpdateErr"));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
        Map<String, String> messages = props.getMessages();
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        List<ProductDto> allProducts = productService.getAllProducts();

        if (allProducts != null) {
            response.setMessage(messages.get("productFetch"));
            response.setStatusCode(200);
            response.setData(allProducts);
        } else {
            response.setMessage(messages.get("productFetchErr"));
            response.setStatusCode(500);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable("id") Integer productId) {
        Map<String, String> messages = props.getMessages();
        ApiResponse<ProductDto> response = new ApiResponse<>();

        ProductDto product = productService.getProductById(productId);

        if (product != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("productSelect"));
            response.setData(product);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("productSelectErr"));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductsByCategoryId(@PathVariable("id") Integer categoryId) {
//        Map<String, String> messages = props.getMessages();
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();

        List<ProductDto> products = productService.getProductsByCategoryId(categoryId);

        if (products != null) {
            response.setStatusCode(200);
            response.setMessage("All Products Fetch by Category Id: " + categoryId);
            response.setData(products);
        } else {
            response.setStatusCode(500);
            response.setMessage("product not found");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> deleteProductById(@PathVariable("id") Integer productId) {

        Map<String, String> messages = props.getMessages();
        ApiResponse<ProductDto> response = new ApiResponse<>();

        ProductDto deleteProductById = productService.deleteProductById(productId);

        if (deleteProductById != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("productDelete"));
            response.setData(deleteProductById);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("productDeleteErr"));
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable("id") Integer productId, @RequestParam("quantity") Integer quantity) {
        Map<String, String> messages = props.getMessages();
        ApiResponse<ProductDto> response = new ApiResponse<>();

        boolean updated = productService.updateStock(productId, quantity);

        if (updated) {
            return ResponseEntity.ok("Stock updated successfully");
        } else {
            return ResponseEntity.ok("Failed to update stock");
        }
    }
}
