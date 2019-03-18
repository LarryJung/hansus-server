package com.hsmchurch.app.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtFactory jwtFactory;

    @Override
    public Authentication authenticate(Authentication jwtPreAuthenticationToken) throws AuthenticationException {
        String jwtString = ((JwtPreAuthorizationToken) jwtPreAuthenticationToken).getToken();
        if (!jwtFactory.isValidateToken(jwtString)) {
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
        return ((JwtPreAuthorizationToken)jwtPreAuthenticationToken).toPostToken(jwtFactory.getUserRole(jwtString));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtPreAuthorizationToken.class.isAssignableFrom(authentication));
    }

}