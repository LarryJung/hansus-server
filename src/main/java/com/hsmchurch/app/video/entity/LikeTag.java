package com.hsmchurch.app.video.entity;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.video.api.dto.request.LikeTagForm;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Getter
@Table(name = "like_tags")
@Entity
public class LikeTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long videoId;

    private Long accountId;

    public LikeTag() {

    }

    private LikeTag(final Long videoId, final Long accountId) {
        this.videoId = videoId;
        this.accountId = accountId;
    }

    public static LikeTag of(final LikeTagForm likeTagForm) {
        return new LikeTag(likeTagForm.getVideoId(), likeTagForm.getAccountId());
    }
}