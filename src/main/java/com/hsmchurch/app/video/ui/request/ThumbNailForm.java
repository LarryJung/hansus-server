package com.hsmchurch.app.video.ui.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ThumbNailForm {

    private final String thumbnailUrl;
    private final int thumbnailWidth;
    private final int thumbnailHeight;

    public ThumbNailForm(String thumbnailUrl, int thumbnailWidth, int thumbnailHeight) {
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

}
