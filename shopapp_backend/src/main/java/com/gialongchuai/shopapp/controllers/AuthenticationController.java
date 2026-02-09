package com.gialongchuai.shopapp.controllers;

import java.text.ParseException;

import com.gialongchuai.shopapp.services.impl.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gialongchuai.shopapp.dtos.request.AuthenticationRequest;
import com.gialongchuai.shopapp.dtos.request.IntroSpectRequest;
import com.gialongchuai.shopapp.dtos.request.LogoutRequest;
import com.gialongchuai.shopapp.dtos.request.RefreshTokenRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.AuthenticationResponse;
import com.gialongchuai.shopapp.dtos.response.IntroSpectResponse;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthenticationController {
    IAuthenticationService iAuthenticationService;

    @PostMapping("/token")
    @Operation(summary = "Authenticate user", description = "Authenticates user and returns JWT token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(iAuthenticationService.authenticate(authenticationRequest))
                .build();
    }

    @PostMapping("/introspect")
    @Operation(summary = "Introspect token", description = "Validates and introspects JWT token")
    public ApiResponse<IntroSpectResponse> introspect(@RequestBody IntroSpectRequest introSpectRequest)
            throws ParseException, JOSEException {
        return ApiResponse.<IntroSpectResponse>builder()
                .result(iAuthenticationService.introspect(introSpectRequest))
                .build();
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout user", description = "Logs out user by invalidating token")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        iAuthenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token", description = "Refreshes JWT token")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        log.info(request.getToken());
        return ApiResponse.<AuthenticationResponse>builder()
                .result(iAuthenticationService.refreshToken(request))
                .build();
    }
}
