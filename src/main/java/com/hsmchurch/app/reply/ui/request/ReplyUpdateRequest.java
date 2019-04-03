package com.hsmchurch.app.reply.ui.request;

import lombok.Value;

@Value
public class ReplyUpdateRequest {

    private final Long replyId;
    private final Long replierId;
    private final String content;

}
