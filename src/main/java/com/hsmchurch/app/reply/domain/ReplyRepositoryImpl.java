package com.hsmchurch.app.reply.domain;

import com.hsmchurch.app.account.domain.QAccount;
import com.hsmchurch.app.reply.ui.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.ui.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class ReplyRepositoryImpl extends QuerydslRepositorySupport implements ReplyRepositoryCustom {

    private static final QReply reply = QReply.reply;
    private static final QAccount account = QAccount.account;

    private final JPAQueryFactory queryFactory;

    public ReplyRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Reply.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ReplyForVideo> videoReplies(Long videoId) {
        return queryFactory.from(reply)
                .select(Projections.fields(ReplyForVideo.class,
                        reply.id.as("id"),
                        reply.content.as("content"),
                        reply.replier.id.as("replierId"),
                        account.name.as("replierName"),
                        reply.createdAt.as("createdAt"),
                        reply.updatedAt.as("updatedAt"))
                )
                .join(reply).on(reply.replier.id.eq(account.id))
                .where(reply.videoId.eq(videoId))
                .fetch();
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
