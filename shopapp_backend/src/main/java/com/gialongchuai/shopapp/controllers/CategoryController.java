package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.CategoryCreationRequest;
import com.gialongchuai.shopapp.dtos.request.CategoryUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.CategoryResponse;
import com.gialongchuai.shopapp.services.CategoryService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/categories")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {
    ICategoryService iCategoryService;

    @PostMapping
    @Operation(summary = "Create category", description = "Creates a new category")
    ApiResponse<CategoryResponse> create(@RequestBody CategoryCreationRequest categoryCreationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.create(categoryCreationRequest))
                .build();
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves all categories")
    ApiResponse<List<CategoryResponse>> getAllCategory() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(iCategoryService.getAllCategories())
                .build();
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "Get category by ID", description = "Retrieves a specific category by ID")
    ApiResponse<CategoryResponse> getCategory(@PathVariable String categoryId) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.getCategory(categoryId))
                .build();
    }

    @PutMapping("/{categoryId}")
    @Operation(summary = "Update category", description = "Updates an existing category")
    ApiResponse<CategoryResponse> updateCategory(
            @PathVariable String categoryId, @RequestBody @Valid CategoryUpdationRequest categoryUpdationRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(iCategoryService.updateCategory(categoryId, categoryUpdationRequest))
                .build();
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Delete category", description = "Deletes a category by ID")
    ApiResponse<String> delete(@PathVariable String categoryId) {
        return ApiResponse.<String>builder()
                .result(iCategoryService.deleteCategory(categoryId))
                .build();
    }
}
