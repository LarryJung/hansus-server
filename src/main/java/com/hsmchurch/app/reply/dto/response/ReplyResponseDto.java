package com.hsmchurch.app.reply.dto.response;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.video.entity.Video;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ReplyResponseDto {

    private final Long id;
    private final Video video;
    private final Account writer;
    private final String content;

}
