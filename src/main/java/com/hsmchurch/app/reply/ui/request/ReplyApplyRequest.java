package com.hsmchurch.app.reply.ui.request;

import lombok.Value;

@Value
public class ReplyApplyRequest {

    private final Long replierId;
    private final String replierName;
    private final Long videoId;
    private final String content;

}
