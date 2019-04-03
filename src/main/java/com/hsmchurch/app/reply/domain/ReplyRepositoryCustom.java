package com.hsmchurch.app.reply.domain;

import com.hsmchurch.app.reply.ui.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.ui.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.ui.response.ReplyResponse;

import java.util.List;

public interface ReplyRepositoryCustom {

    List<ReplyResponse> videoReplies(final Long videoId);

    long deleteReply(final ReplyDeleteRequest deleteForm);

    long deleteReplies(final TargetVideoRepliesDeleteRequest deleteForm);

}
