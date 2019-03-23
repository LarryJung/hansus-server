package com.hsmchurch.app.security;

import com.hsmchurch.app.security.oauth.Oauth2FetchService;
import com.hsmchurch.app.security.oauth.SocialFetchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OauthConfiguration {

    @Bean("SocialFetchService")
    public SocialFetchService socialFetchService() {
        return new Oauth2FetchService();
    }

}
