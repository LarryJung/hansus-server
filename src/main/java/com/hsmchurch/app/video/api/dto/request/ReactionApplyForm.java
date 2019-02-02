package com.hsmchurch.app.video.api.dto.request;

import lombok.Value;


@Value
public class ReactionApplyForm {

    private final Long videoId;
    private final Long accountId;
    private final Long reactionId;

    public ReactionApplyForm(final Long videoId, final Long accountId, final Long reactionId) {
        this.videoId = videoId;
        this.accountId = accountId;
        this.reactionId = reactionId;
    }
}
