package com.ecommerce.ecommerce.constants;

public class SecurityConstants {
    public static final String SIGN_UP_URL = "/user/api/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    // space must required after prefix token
    public static final String TOKEN_PREFIX = "E-commerce ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 600_000;

}
