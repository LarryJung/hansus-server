package com.hsmchurch.app.security;

import com.hsmchurch.app.account.domain.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper {

    private AuthenticationHelper() {
    }

    private static Authentication authentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long currentUserId() {
        return (Long) authentication().getPrincipal();
    }

    public static boolean currentUserIsAdmin() {
        return authentication().getAuthorities().stream()
                .anyMatch(r -> Role.fromAuthority(r.getAuthority()).equals(Role.ADMIN));
    }
}