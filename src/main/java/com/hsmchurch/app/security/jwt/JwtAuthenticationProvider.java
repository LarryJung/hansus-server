package com.hsmchurch.app.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtFactory jwtFactory;

    @Override
    public Authentication authenticate(Authentication PreAuthenticationToken) throws AuthenticationException {
        if (PreAuthenticationToken.getPrincipal() == null) {
            log.info("토큰이 들어있지 않습니다.");
            return null;
        }
        final String jwtString = ((JwtPreAuthorizationToken) PreAuthenticationToken).getToken();
        if (!jwtFactory.isValidateToken(jwtString)) {
            log.info("유효하지 않은 토큰입니다. token: {}", jwtString);
            return null;
        }
        return ((JwtPreAuthorizationToken)PreAuthenticationToken).toPostToken(jwtFactory.getUserRole(jwtString));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtPreAuthorizationToken.class.isAssignableFrom(authentication));
    }

}