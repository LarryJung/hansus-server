package com.hsmchurch.app.reply.entity.repository;

import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponse;

import java.util.List;

public interface ReplyRepositoryCustom {

    List<ReplyResponse> videoReplies(final Long videoId);

    long deleteReply(final ReplyDeleteRequest deleteForm);

    long deleteReplies(final TargetVideoRepliesDeleteRequest deleteForm);

}
