package com.hsmchurch.app.reply.ui.request;

import lombok.Value;

@Value
public class TargetVideoRepliesDeleteRequest {

    private final Long videoId;
    private final Long replierId;

    public TargetVideoRepliesDeleteRequest(final Long videoId,
                                           final Long replierId) {
        this.videoId = videoId;
        this.replierId = replierId;
    }
}
