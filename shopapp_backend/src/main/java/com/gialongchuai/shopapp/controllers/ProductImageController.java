package com.gialongchuai.shopapp.controllers;

import com.gialongchuai.shopapp.dtos.request.ProductCreationRequest;
import com.gialongchuai.shopapp.dtos.response.ProductResponse;
import com.gialongchuai.shopapp.services.impl.IProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.services.ProductImageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/products/images")
@Tag(name = "ProductImage", description = "Product image management APIs")
public class ProductImageController {
    IProductImageService iProductImageService;

    @DeleteMapping("/{productImageId}")
    @Operation(summary = "Delete product image", description = "Deletes a product image by ID")
    ApiResponse<String> delete(@PathVariable String productImageId) {
        return ApiResponse.<String>builder()
                .result(iProductImageService.deleteProductImage(productImageId))
                .build();
    }
}
