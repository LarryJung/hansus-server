package com.hsmchurch.app.reply.api.dto.request;

import lombok.Value;

@Value
public class ReplyDeleteForm {

    private final Long replyId;
    private final Long writerId;

    public ReplyDeleteForm(final Long replyId, final Long writerId) {
        this.replyId = replyId;
        this.writerId = writerId;
    }
}
