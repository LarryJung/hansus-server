package com.hsmchurch.app.reply.dto.request;

import lombok.Data;
import lombok.Value;

@Value
public class ReplyUpdateForm {

    private final Long id;
    private final String content;

}
