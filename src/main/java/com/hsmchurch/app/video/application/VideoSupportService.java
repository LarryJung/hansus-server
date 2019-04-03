package com.hsmchurch.app.video.application;

import com.hsmchurch.app.video.domain.LikeTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoSupportService {

    private final LikeTagService likeTagService;

    public Page<Long> likeList(final Long accountId,
                                  final Pageable pageable) {
        final Page<Long> videoIds = likeTagService.findAllByAccountId(accountId, pageable)
                .map(LikeTag::getVideoId);

        return videoIds;
    }
}
