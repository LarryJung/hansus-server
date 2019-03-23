package com.hsmchurch.app.video.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class Thumbnail {

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "thumbnail_width")
    private int thumbnailWidth;

    @Column(name = "thumbnail_height")
    private int thumbnailHeight;

    private Thumbnail(final String thumbnailUrl,
                      final int thumbnailWidth,
                      final int thumbnailHeight) {
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
    }

    public static Thumbnail of(final String thumbnailUrl,
                               final int thumbnailWidth,
                               final int thumbnailHeight) {
        return new Thumbnail(thumbnailUrl, thumbnailWidth, thumbnailHeight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail = (Thumbnail) o;
        return thumbnailWidth == thumbnail.thumbnailWidth &&
                thumbnailHeight == thumbnail.thumbnailHeight &&
                Objects.equals(thumbnailUrl, thumbnail.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thumbnailUrl, thumbnailWidth, thumbnailHeight);
    }
}
