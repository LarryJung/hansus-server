package com.hsmchurch.app.security.oauth;

public interface SocialFetchService {

    SocialUserProperty getSocialUserInfo(SocialLoginDto dto);
}
