package com.hsmchurch.app.video.application;

import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.video.domain.LikeTag;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VideoSupportService {

    private final LikeTagService likeTagService;
    private final ReplyService replyService;

    public List<Long> likeList(final Long accountId) {
        return likeTagService.findAllByAccountId(accountId).stream()
                .map(LikeTag::getVideoId)
                .collect(toList());
    }

    public List<LikeUser> likeUsers(final Long videoId) {
        return likeTagService.findAllByVideo(videoId);
    }

    public List<ReplyForVideo> replies(final Long id) {
        return replyService.findAllByVideo2(id);
    }
}
