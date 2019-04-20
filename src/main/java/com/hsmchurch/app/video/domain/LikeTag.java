package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.common.BaseEntity;
import com.hsmchurch.app.video.ui.request.LikeTagRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Table(name = "like_tags")
@Entity
public class LikeTag extends BaseEntity {

   @EmbeddedId
   private LikeTagId likeTagId;

    public LikeTag(final LikeTagId likeTagId) {
        this.likeTagId = likeTagId;
    }

    public static LikeTag of(final LikeTagRequest likeTagRequest) {
        return new LikeTag(new LikeTagId(likeTagRequest.getVideoId(), likeTagRequest.getAccountId()));
    }

    public void cancel() {
        markAsDeleted();
    }

    public Long getVideoId() {
        return likeTagId.getVideoId();
    }

    public Long getWriterId() {
        return likeTagId.getAccountId();
    }
}