package com.hsmchurch.app.noticeboard.domain;

import com.hsmchurch.app.account.domain.QAccount;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import com.hsmchurch.app.reply.domain.Reply;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NoticeRepositoryImpl extends QuerydslRepositorySupport implements NoticeRepositoryCustom {

    private static final QNotice notice = QNotice.notice;
    private static final QAccount account = QAccount.account;
    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Reply.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<NoticeResponse> findListAll(Pageable pageable) {
        final QueryResults<NoticeResponse> queryResults = queryFactory.from(notice)
                .select(qNoticeResponce())
                .join(notice).on(notice.writer.id.eq(account.id))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();
        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public NoticeResponse findOneWithWriterName(Long noticeId) {
        return queryFactory.from(notice)
                .select(qNoticeResponce())
                .join(notice).on(notice.writer.id.eq(account.id))
                .where(notice.id.eq(noticeId))
                .fetchOne();
    }

    private static QBean<NoticeResponse> qNoticeResponce() {
        return Projections.fields(NoticeResponse.class,
                notice.id.as("id"),
                notice.title.as("title"),
                notice.content.as("content"),
                account.id.as("writerId"),
                account.name.as("name"),
                notice.createdAt.as("createdAt"),
                notice.updatedAt.as("updatedAt")
        );
    }
}
