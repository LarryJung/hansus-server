package com.hsmchurch.app.reply.ui.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ReplyResponse {

    private final Long id;
    private final Long videoId;
    private final Long replierId;
    private final String content;

}
