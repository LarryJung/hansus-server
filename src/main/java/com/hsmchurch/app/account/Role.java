package com.hsmchurch.app.account;

import lombok.Getter;
import org.springframework.security.access.AuthorizationServiceException;

import java.util.Arrays;

@Getter
public enum Role {

    ADMIN,
    MEMBER;

    private static final String PREFIX_AUTHORITY = "ROLE_";

    public String authority() {
        return PREFIX_AUTHORITY + name();
    }

    public static Role fromAuthority(final String authority) {
        return Arrays.stream(values())
                .filter(role ->
                        role.authority().equals(authority)
                )
                .findFirst()
                .orElseThrow(() ->
                        new AuthorizationServiceException(authority)
                );
    }

}
