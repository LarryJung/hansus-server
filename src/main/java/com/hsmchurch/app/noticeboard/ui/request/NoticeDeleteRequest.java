package com.hsmchurch.app.noticeboard.ui.request;

import lombok.Value;

@Value
public class NoticeDeleteRequest {

    final private Long boardId;

    final private Long writerId;

}
