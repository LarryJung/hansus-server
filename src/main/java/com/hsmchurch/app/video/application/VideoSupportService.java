package com.hsmchurch.app.video.application;

import com.hsmchurch.app.account.application.AccountService;
import com.hsmchurch.app.reply.application.ReplyService;
import com.hsmchurch.app.video.domain.LikeTag;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoSupportService {

    private final LikeTagService likeTagService;
    private final ReplyService replyService;

    public Page<Long> likeList(final Long accountId,
                               final Pageable pageable) {
        final Page<Long> videoIds = likeTagService.findAllByAccountId(accountId, pageable)
                .map(LikeTag::getVideoId);

        return videoIds;
    }

    public List<LikeUser> likeUsers(final Long videoId) {
        return likeTagService.findAllByVideo(videoId);
    }

    public List<ReplyForVideo> replies(final Long id) {
        return replyService.findAllByVideo2(id);
    }
}
