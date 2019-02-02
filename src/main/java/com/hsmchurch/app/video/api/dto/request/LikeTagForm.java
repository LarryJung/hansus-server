package com.hsmchurch.app.video.api.dto.request;

import lombok.Value;

@Value
public class LikeTagForm {

    private final Long videoId;
    private final Long accountId;

    public LikeTagForm(final Long videoId, final Long accountId) {
        this.videoId = videoId;
        this.accountId = accountId;
    }

}
