package com.hsmchurch.app.video.api.dto.request;

import lombok.Value;


@Value
public class ReactionApplyForm {

    private final Long videoId;
    private final Long userId;
    private final Long reactionId;

    public ReactionApplyForm(final Long videoId, final Long userId, final Long reactionId) {
        this.videoId = videoId;
        this.userId = userId;
        this.reactionId = reactionId;
    }
}
