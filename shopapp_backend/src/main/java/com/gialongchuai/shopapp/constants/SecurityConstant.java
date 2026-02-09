package com.gialongchuai.shopapp.constants;

public class SecurityConstant {
    public static final String[] PUBLIC_ENDPOINTS = {
            // Swagger - Without context-path prefix (Spring Security sees paths without /shopapp)
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/webjars/**",

            // API public
            "/categories", "/categories/{categoryId}",
            "/products", "/products/{productId}",
            "/users/registration",
            "/auth/token", "/auth/introspect",
            "/auth/logout", "/auth/refresh",

            // Static
            "/uploads/**"
    };

    public static final String JWT_AUTHORITY_PREFIX = "";
    public static final int BCRYPT_STRENGTH = 10;
    }
