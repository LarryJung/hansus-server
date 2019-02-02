package com.hsmchurch.app.video.entity.value;

import lombok.Value;

import javax.persistence.Embeddable;

@Value
@Embeddable
public class ThumbNail {

    private final String thumbnailUrl;
    private final int thumbnailWidth;
    private final int thumbnailHeight;

    private ThumbNail(final String thumbnailUrl, final int thumbnailWidth, final int thumbnailHeight) {
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public static ThumbNail of(final String thumbnailUrl, final int thumbnailWidth, final int thumbnailHeight) {
        return new ThumbNail(thumbnailUrl, thumbnailWidth, thumbnailHeight);
    }

}
