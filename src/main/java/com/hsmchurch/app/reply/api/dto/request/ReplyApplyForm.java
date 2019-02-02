package com.hsmchurch.app.reply.api.dto.request;

import lombok.Data;

@Data
public class ReplyApplyForm {

    private Long writerId;

    private Long videoId;

    private String content;

}
