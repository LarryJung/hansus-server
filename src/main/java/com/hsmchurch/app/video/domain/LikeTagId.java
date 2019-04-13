package com.hsmchurch.app.video.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor
@Embeddable
public class LikeTagId implements Serializable {

    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "account_id")
    private Long accountId;

    public LikeTagId(final Long videoId,
                     final Long accountId) {
        this.videoId = videoId;
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeTagId likeTagId = (LikeTagId) o;
        return Objects.equals(videoId, likeTagId.videoId) &&
                Objects.equals(accountId, likeTagId.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, accountId);
    }
}
