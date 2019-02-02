package com.hsmchurch.app.reply.dto.request;

import lombok.Data;

@Data
public class ReplyForm {

    private Long writerId;

    private Long videoId;

    private String content;

}
