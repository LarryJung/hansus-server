package com.hsmchurch.app.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;

    protected SocialLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public SocialLoginFilter(String url, AuthenticationSuccessHandler handler) {
        super(url);
        this.successHandler = handler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SocialLoginDto dto = objectMapper.readValue(req.getReader(), SocialLoginDto.class);
        log.info("input provider : {}", dto.getProvider());
        log.info("input token : {}", dto.getToken());
        return super.getAuthenticationManager().authenticate(new SocialPreAuthorizationToken(dto));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, authResult);
    }
}
