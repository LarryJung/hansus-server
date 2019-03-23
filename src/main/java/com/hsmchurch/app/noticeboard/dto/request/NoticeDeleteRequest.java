package com.hsmchurch.app.noticeboard.dto.request;

import lombok.Value;

@Value
public class NoticeDeleteRequest {

    final private Long boardId;

    final private Long writerId;

}
