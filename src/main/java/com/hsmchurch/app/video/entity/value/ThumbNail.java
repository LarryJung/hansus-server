package com.hsmchurch.app.video.entity.value;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
public class ThumbNail {

    @Column
    private String thumbnailUrl;

    @Column
    private int thumbnailWidth;

    @Column
    private int thumbnailHeight;

    private ThumbNail(String thumbnailUrl, int thumbnailWidth, int thumbnailHeight) {
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public static ThumbNail of(String thumbnailUrl, int thumbnailWidth, int thumbnailHeight) {
        return new ThumbNail(thumbnailUrl, thumbnailWidth, thumbnailHeight);
    }

}
