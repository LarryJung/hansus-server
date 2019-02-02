package com.hsmchurch.app.video;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
//@Profile("!db-test")
public class GlobalValue {

    public static String YOUTUBE_CLIENT_KEY;
    public static String YOUTUBE_API_URL;
    public static String YOUTUBE_CHANNEL_ID;

    @Value("${youtube.key}")
    public void setYoutubeClientKey(String youtubeClientKey) {
        YOUTUBE_CLIENT_KEY = youtubeClientKey;
    }

    @Value("${youtube.url}")
    public void setYoutubeApiUrl(String youtubeApiUrl) {
        YOUTUBE_API_URL = youtubeApiUrl;
    }

    @Value("${youtube.channelId}")
    public void setYoutubeChannelId(String channelId) {
        YOUTUBE_CHANNEL_ID = channelId;
    }

}
