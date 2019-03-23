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
            log.info("토큰이 들어있지 않습니다.");
            return null;
        }
        if (payload.length() < HEADER_PREFIX.length()) {
            log.info("토큰 길이가 유효하지 않습니다. : " + payload);
            return null;
        }
        return payload.substring(HEADER_PREFIX.length(), payload.length());
    }

    public Long extractId(HttpServletRequest request) {
        String token = extract(request);
        if (token == null) {
            return null;
        }
        return jwtFactory.getIdFromToken(token);
    }

}
