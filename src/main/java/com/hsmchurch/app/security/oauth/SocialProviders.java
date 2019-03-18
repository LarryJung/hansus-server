package com.hsmchurch.app.security.oauth;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SocialProviders {

    KAKAO("https://kapi.kakao.com/v2/user/me", KakaoUserProperty.class);

    private String userinfoEndpoint;
    private Class<? extends SocialUserProperty> propertyMetaclass;

    SocialProviders(String userinfoEndpoint, Class<? extends SocialUserProperty> propertyMetaclass) {
        this.userinfoEndpoint = userinfoEndpoint;
        this.propertyMetaclass = propertyMetaclass;
    }

    @JsonValue
    public String getProviderName() {
        return this.name();
    }

}
