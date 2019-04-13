package com.hsmchurch.app.reply.ui.request;

import lombok.Value;

@Value
public class ReplyDeleteRequest {

    private final Long replyId;
    private final Long replierId;

    public ReplyDeleteRequest(final Long replyId,
                              final Long replierId) {
        this.replyId = replyId;
        this.replierId = replierId;
    }
}
