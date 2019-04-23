package com.hsmchurch.app.reply.domain;

import com.hsmchurch.app.reply.ui.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.ui.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;

import java.util.List;

public interface ReplyRepositoryCustom {

    List<ReplyForVideo> videoReplies(final Long videoId);

    long deleteReply(final ReplyDeleteRequest deleteForm);

    long deleteReplies(final TargetVideoRepliesDeleteRequest deleteForm);

}
