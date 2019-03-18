package com.hsmchurch.app.security.jwt;

import com.hsmchurch.app.security.account.entity.value.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class JwtPreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public JwtPreAuthorizationToken(Object principal, Object credentials) {
        super(principal, credentials);
        log.info("principal (Id) : {}", principal);
        log.info("credentials (token) : {}", credentials);
    }

    public Long getId() {
        return (Long) this.getPrincipal();
    }

    public String getToken() {
        return (String) this.getCredentials();
    }

    public Authentication toPostToken(Role role) {
        log.info("pre to post token");
        return new JwtPostAuthorizationToken(getId(), getToken(), parseAuthorities(role));
    }

    private List<SimpleGrantedAuthority> parseAuthorities(Role role) {
        log.info("roles {}", role.toString());
        return Stream.of(role).map(r -> new SimpleGrantedAuthority(r.name())).collect(toList());
    }

}
