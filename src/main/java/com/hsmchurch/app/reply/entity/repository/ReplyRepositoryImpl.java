package com.hsmchurch.app.reply.entity.repository;

import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponse;
import com.hsmchurch.app.reply.entity.QReply;
import com.hsmchurch.app.reply.entity.Reply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QReply Q_REPLY = QReply.reply;

    public List<ReplyResponse> videoReplies(final Long videoId) {
        return queryFactory.select(Q_REPLY)
                .where(Q_REPLY.video.id.eq(videoId))
                .fetch().stream().map(Reply::toResponse)
                .collect(toList());
    }

    public long deleteReply(final ReplyDeleteRequest deleteForm) {
        return queryFactory.delete(Q_REPLY)
                .where(
                        Q_REPLY.id.eq(deleteForm.getReplyId())
                                .and(Q_REPLY.writer.id.eq(deleteForm.getReplierId()))
                )
                .execute();
    }

    public long deleteReplies(final TargetVideoRepliesDeleteRequest deleteForm) {
        return queryFactory.delete(Q_REPLY)
                .where(
                        Q_REPLY.video.id.eq(deleteForm.getVideoId())
                                .and(Q_REPLY.writer.id.eq(deleteForm.getReplierId()))
                )
                .execute();
    }
}
