package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.entity.LikeTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VideoSupportService {

    private final LikeTagService likeTagService;

    public List<Long> likeList(final Long accountId) {
        final List<Long> videoIds = likeTagService.findAllByAccountId(accountId).stream()
                .map(LikeTag::getVideoId).collect(toList());
        return videoIds;
    }
}
