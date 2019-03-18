package com.hsmchurch.app.reply.entity.repository;

import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteForm;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteForm;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponseDto;

import java.util.List;

public interface ReplyRepositoryCustom {

    List<ReplyResponseDto> videoReplies(final Long videoId);

    long deleteReply(final ReplyDeleteForm deleteForm);

    long deleteReplies(final TargetVideoRepliesDeleteForm deleteForm);

}
