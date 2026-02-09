package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.UserCreationRequest;
import com.gialongchuai.shopapp.dtos.request.UserUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.UserResponse;
import com.gialongchuai.shopapp.services.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "User", description = "User management APIs")
public class UserController {
    IUserService iUserService;

    @PostMapping("/registration")
    @Operation(summary = "Create user", description = "Creates a new user account")
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        log.info("-------i am here");
        return ApiResponse.<UserResponse>builder()
                .result(iUserService.createUser(userCreationRequest))
                .build();
    }

    @GetMapping
    List<UserResponse> getAllUser() {
        return iUserService.getAllUser();
    }

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable String userId) {
        return iUserService.getUser(userId);
    }

    @GetMapping("/myinfo")
    UserResponse getMyInfo() {
        return iUserService.getMyInfo();
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdationRequest userUpdationRequest) {
        log.info("== Result: " + userId + "==" + userUpdationRequest);
        return iUserService.updateUser(userId, userUpdationRequest);
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        return ApiResponse.<String>builder()
                .message(iUserService.deleteUser(userId))
                .build();
    }
}
