package com.hsmchurch.app.video.api.dto.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class YoutubeForm {

    private final String id;
    private final String publishedAt;
    private final String title;
    private final String description;
    private final ThumbNailForm thumbNail;

}
