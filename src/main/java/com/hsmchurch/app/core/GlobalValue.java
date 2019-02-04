package com.hsmchurch.app.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalValue {

    public static String YOUTUBE_CLIENT_KEY;
    public static String YOUTUBE_API_URL;
    public static String YOUTUBE_CHANNEL_ID;
    public static String KAKAO_CLIENT_ID;
    public static String KAKAO_REDIRECT_URI;
    public static String KAKAO_OAUTH_HOST;

    @Value("${youtube.key}")
    public void setYoutubeClientKey(String youtubeClientKey) {
        YOUTUBE_CLIENT_KEY = youtubeClientKey;
    }

    @Value("${youtube.url}")
    public void setYoutubeApiUrl(String youtubeApiUrl) {
        YOUTUBE_API_URL = youtubeApiUrl;
    }

    @Value("${youtube.channel-id}")
    public void setYoutubeChannelId(String channelId) {
        YOUTUBE_CHANNEL_ID = channelId;
    }

    @Value("${security.oauth.redirect-uri}")
    public void setKakaoRedirectUri(String redirectUri) {
        KAKAO_REDIRECT_URI = redirectUri;
    }

    @Value("${security.oauth.client-id.kakao}")
    public void setKakaoClientId(String clientId) {
        KAKAO_CLIENT_ID = clientId;
    }

    @Value("${security.oauth.host.kakao}")
    public void setKakaoHost(String host) {
        KAKAO_OAUTH_HOST = host;
    }

}
