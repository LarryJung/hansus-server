package com.hsmchurch.app.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class HeaderTokenExtractor {

    @Autowired
    private JwtFactory jwtFactory;

    public static final String HEADER_PREFIX = "Bearer ";

    public String extract(HttpServletRequest request) {
        log.info("path , method : {}, {} ", request.getPathInfo(), request.getMethod());
        String payload = request.getHeader("Authorization");
        if(StringUtils.isEmpty(payload)) {
            throw new RuntimeException("토큰이 들어있지 않습니다.");
        }
        if (payload.length() < HEADER_PREFIX.length()) {
            throw new RuntimeException("토큰 길이가 유효하지 않습니다. : " + payload);
        }
        return payload.substring(HEADER_PREFIX.length(), payload.length());
    }

    public Long extractId(HttpServletRequest request) {
        String token = extract(request);
        return jwtFactory.getIdFromToken(token);
    }

}
