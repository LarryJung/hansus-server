package com.hsmchurch.app.noticeboard.ui.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MyNoticeResponse {

    private final Long id;
    private final String title;
    private final String content; // 단순 글이 아닐 것이므로 향후 객체화 가능

}
