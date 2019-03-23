package com.hsmchurch.app.video.api.dto.request;

import lombok.Value;

@Value
public class LikeTagRequest {

    private final Long videoId;
    private final Long accountId;

    public LikeTagRequest(final Long videoId,
                          final Long accountId) {
        this.videoId = videoId;
        this.accountId = accountId;
    }

}
