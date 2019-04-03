package com.hsmchurch.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {

    private AuthenticationHelper() {
    }

    private static Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String currentUsername() {
        return authentication().getPrincipal().toString();
    }

}