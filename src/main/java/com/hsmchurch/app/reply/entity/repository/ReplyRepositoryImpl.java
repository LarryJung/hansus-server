package com.hsmchurch.app.reply.entity.repository;

import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    @Override
    public List<ReplyResponse> videoReplies(Long videoId) {
        return null;
    }

    @Override
    public long deleteReply(ReplyDeleteRequest deleteForm) {
        return 0;
    }

    @Override
    public long deleteReplies(TargetVideoRepliesDeleteRequest deleteForm) {
        return 0;
    }
}
