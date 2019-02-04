package com.hsmchurch.app.security.oauth;

import lombok.Getter;

import static com.hsmchurch.app.core.GlobalValue.*;

@Getter
public enum ServiceProviderInfo {

    KAKAO(KAKAO_OAUTH_HOST, KAKAO_CLIENT_ID, KAKAO_REDIRECT_URI, "code");

    private final String oAuthHost;
    private final String clientId;
    private final String redirectUri;
    private final String responseType;

    ServiceProviderInfo(final String oAuthHost, final String clientId, final String redirectUri, final String responseType) {
        this.oAuthHost = oAuthHost;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.responseType = responseType;
    }
}
