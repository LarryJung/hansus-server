package com.hsmchurch.app.video.ui.request;

import lombok.Value;


@Value
public class ReactionApplyRequest {

    private final Long videoId;
    private final Long accountId;
    private final Long reactionId;
    private final String reactionName;
    private final String reactionImageUrl;

    public ReactionApplyRequest(final Long videoId,
                                final Long accountId,
                                final Long reactionId,
                                final String reactionName,
                                final String reactionImageUrl) {
        this.videoId = videoId;
        this.accountId = accountId;
        this.reactionId = reactionId;
        this.reactionName = reactionName;
        this.reactionImageUrl = reactionImageUrl;
    }
}
