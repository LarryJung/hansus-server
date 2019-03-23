package com.hsmchurch.app.noticeboard.dto.response;

import com.hsmchurch.app.noticeboard.entity.Writer;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NoticeResponse {

    private final Long id;

    private final String title;

    private final String content; // 단순 글이 아닐 것이므로 향후 객체화 가능

    private final Writer writer;

}
