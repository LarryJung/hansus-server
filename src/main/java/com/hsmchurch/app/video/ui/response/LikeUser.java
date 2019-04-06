package com.hsmchurch.app.video.ui.response;

import lombok.Value;

@Value
public class LikeUser {

    private final Long userId;
    private final String userName;

    public LikeUser(final Long userId,
                    final String userName) {
        this.userId = userId;
        this.userName = userName;
    }

}
