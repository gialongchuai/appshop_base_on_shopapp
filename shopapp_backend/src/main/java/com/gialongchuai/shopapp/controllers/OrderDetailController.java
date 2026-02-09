package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IOrderDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.OrderDetailCreationRequest;
import com.gialongchuai.shopapp.dtos.request.OrderDetailUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.OrderDetailResponse;
import com.gialongchuai.shopapp.services.OrderDetailService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "OrderDetail", description = "Order detail management APIs")
public class OrderDetailController {
    IOrderDetailService iOrderDetailService;

    @PostMapping("/orders/details")
    @Operation(summary = "Create order detail", description = "Creates a new order detail")
    ApiResponse<OrderDetailResponse> create(@RequestBody @Valid OrderDetailCreationRequest orderDetailCreationRequest) {
        log.info("=== create order: " + orderDetailCreationRequest);
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.create(orderDetailCreationRequest))
                .build();
    }

    @GetMapping("/orders/details")
    ApiResponse<List<OrderDetailResponse>> getAllOrderDetail() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(iOrderDetailService.getAllOrderDetails())
                .build();
    }

    @GetMapping("/orders/order_details/{order_id}")
    ApiResponse<List<OrderDetailResponse>> getAllOrderDetailOfOrder(@PathVariable("order_id") String orderId){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(iOrderDetailService.getAllOrderDetailsOfOrder(orderId))
                .build();
    }

    @GetMapping("/orders/details/{orderDetailId}")
    ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable String orderDetailId) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.getOrderDetail(orderDetailId))
                .build();
    }

    @PutMapping("/orders/details/{orderDetailId}")
    ApiResponse<OrderDetailResponse> updateOrderDetail(
            @PathVariable String orderDetailId,
            @RequestBody @Valid OrderDetailUpdationRequest orderDetailUpdationRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(iOrderDetailService.updateOrderDetail(orderDetailId, orderDetailUpdationRequest))
                .build();
    }

    @DeleteMapping("/orders/details/{orderDetailId}")
    ApiResponse<String> delete(@PathVariable String orderDetailId) {
        return ApiResponse.<String>builder()
                .result(iOrderDetailService.deleteOrderDetail(orderDetailId))
                .build();
    }
}
