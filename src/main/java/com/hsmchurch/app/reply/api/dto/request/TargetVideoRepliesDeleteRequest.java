package com.hsmchurch.app.reply.api.dto.request;

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
