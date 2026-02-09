package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.OrderCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.OrderResponse;
import com.gialongchuai.shopapp.services.OrderService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/orders")
@Tag(name = "Order", description = "Order management APIs")
public class OrderController {
    IOrderService iOrderService;

    @PostMapping
    @Operation(summary = "Create order", description = "Creates a new order")
    ApiResponse<OrderResponse> create(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {
        log.info("=== create order: " + orderCreationRequest);
        return ApiResponse.<OrderResponse>builder()
                .result(iOrderService.create(orderCreationRequest))
                .build();
    }

    @GetMapping
    @Operation(summary = "Get all orders", description = "Retrieves all orders")
    ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(iOrderService.getAllOrders())
                .build();
    }

    @GetMapping("/user/{user_id}")
    ApiResponse<List<OrderResponse>> getAllOrdersByUserId(@PathVariable("user_id") String userId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(iOrderService.getAllOrdersByUserId(userId))
                .build();
    }

    @GetMapping("/{orderId}")
    ApiResponse<OrderResponse> getOrder(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(iOrderService.getOrder(orderId))
                .build();
    }

    @PutMapping("/{orderId}")
    ApiResponse<OrderResponse> updateOrder(
            @PathVariable String orderId, @RequestBody @Valid OrderUpdationRequest orderUpdationRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(iOrderService.updateOrder(orderId, orderUpdationRequest))
                .build();
    }

    @DeleteMapping("/{orderId}")
    ApiResponse<String> delete(@PathVariable String orderId) {
        return ApiResponse.<String>builder()
                .result(iOrderService.deleteOrder(orderId))
                .build();
    }
}
