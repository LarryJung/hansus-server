package com.hsmchurch.app.security.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Slf4j
@Service
public class Oauth2FetchService implements SocialFetchService{

    private static final String HEADER_PREFIX = "Bearer ";

    @Override
    public SocialUserProperty getSocialUserInfo(SocialLoginDto dto) {
        SocialProviders provider = dto.getProvider();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>("parameter", generateHeader(dto.getToken()));
        log.info("entity : {}", entity);
        return restTemplate.exchange(provider.getUserinfoEndpoint(), HttpMethod.GET, entity, provider.getPropertyMetaclass()).getBody();
    }

    private HttpHeaders generateHeader(String token) {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", generateHeaderContent(token));
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        return header;
    }

    private String generateHeaderContent(String token) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER_PREFIX);
        sb.append(token);
        return sb.toString();
    }

}
