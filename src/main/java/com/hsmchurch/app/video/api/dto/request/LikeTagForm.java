package com.hsmchurch.app.video.api.dto.request;

import lombok.Value;

@Value
public class LikeTagForm {

    private final Long videoId;
    private final Long userId;

    public LikeTagForm(final Long videoId, final Long userId) {
        this.videoId = videoId;
        this.userId = userId;
    }

}
