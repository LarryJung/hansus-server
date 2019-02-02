package com.hsmchurch.app.reply.api.dto.request;

import lombok.Value;

@Value
public class TargetVideoRepliesDeleteForm {

    private final Long videoId;
    private final Long writerId;

    public TargetVideoRepliesDeleteForm(final Long videoId, final Long writerId) {
        this.videoId = videoId;
        this.writerId = writerId;
    }
}
