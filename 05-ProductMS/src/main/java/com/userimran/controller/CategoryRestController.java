package com.userimran.controller;

import com.userimran.dto.CategoryDto;
import com.userimran.props.AppProps;
import com.userimran.response.ApiResponse;
import com.userimran.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category/api")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final AppProps props;

    @PostMapping("/category")
    public ResponseEntity<ApiResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto) {

        Map<String, String> messages = props.getMessages();
        ApiResponse<CategoryDto> response = new ApiResponse<>();

        CategoryDto addedCategory = categoryService.addCategory(categoryDto);

        if (addedCategory != null) {
            response.setStatusCode(201);
            response.setMessage(messages.get("categoryAdded"));
            response.setData(addedCategory);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("categoryAddErr"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {

        Map<String, String> messages = props.getMessages();
        ApiResponse<List<CategoryDto>> response = new ApiResponse<>();

        List<CategoryDto> categories = categoryService.getAllCategories();

        if (categories != null) {
            response.setMessage(messages.get("categoryFetch"));
            response.setStatusCode(200);
            response.setData(categories);
        } else {
            response.setMessage(messages.get("categoryFetchErr"));
            response.setStatusCode(200);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable("id") Integer categoryId) {
        Map<String, String> messages = props.getMessages();
        ApiResponse<CategoryDto> response = new ApiResponse<>();

        CategoryDto category = categoryService.getCategoryById(categoryId);

        if (category != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("categorySelect"));
            response.setData(category);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("categorySelectErr"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable("id") Integer categoryId,
                                                                   @RequestBody CategoryDto categoryDto) {

        Map<String, String> messages = props.getMessages();
        CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (updatedCategory != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("categoryUpdate"));
            response.setData(updatedCategory);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("categoryUpdateErr"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> deleteCategoryById(@PathVariable("id") Integer categoryId) {

        Map<String, String> messages = props.getMessages();
        CategoryDto deletedCategory = categoryService.deleteCategoryById(categoryId);

        ApiResponse<CategoryDto> response = new ApiResponse<>();

        if (deletedCategory != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get("categoryDelete"));
            response.setData(deletedCategory);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get("categoryDeleteErr"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
