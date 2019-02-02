package com.hsmchurch.app.reply.api.dto.request;

import lombok.Value;

@Value
public class ReplyUpdateForm {

    private final Long replyId;
    private Long writerId;
    private final String content;

}
