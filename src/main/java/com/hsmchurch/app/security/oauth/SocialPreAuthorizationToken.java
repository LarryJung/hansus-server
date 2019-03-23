package com.hsmchurch.app.security.oauth;

import com.hsmchurch.app.account.domain.Account;
import com.hsmchurch.app.account.domain.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SocialPreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public SocialPreAuthorizationToken(SocialLoginDto dto) {
        super(dto.getProvider(), dto);
    }

    public SocialLoginDto getDto() {
        return (SocialLoginDto)super.getCredentials();
    }

    public Authentication toPostToken(Account account) {
        return new PostAuthorizationToken(account.getId(), account.getName(), parseAuthorities(account.getRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(Role role) {
        return Stream.of(role).map(s -> new SimpleGrantedAuthority(s.name())).collect(toList());
    }
}
