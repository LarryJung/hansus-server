package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.video.ui.response.LikeUser;

import java.util.List;

public interface LikeTagRepositoryCustom {

    List<LikeUser> findAllByVideo(Long video);
}
