package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.account.domain.QAccount;
import com.hsmchurch.app.video.ui.response.LikeUser;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.emptyList;

@Repository
@Transactional
public class LikeTagRepositoryImpl extends QuerydslRepositorySupport implements LikeTagRepositoryCustom {

    private static final QLikeTag likeTag = QLikeTag.likeTag;
    private static final QAccount account = QAccount.account;

    private final JPAQueryFactory queryFactory;

    public LikeTagRepositoryImpl(JPAQueryFactory queryFactory) {
        super(LikeTag.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<LikeUser> findAllByVideo(final long video) {
        List<LikeUser> result =  queryFactory.from(likeTag)
                .select(Projections.fields(LikeUser.class,
                        account.id.as("userId"),
                        account.name.as("userName"))
                )
                .join(likeTag).on(likeTag.likeTagId.accountId.eq(account.id))
                .where(likeTag.likeTagId.videoId.eq(video))
                .fetch();
        return result != null ? result : emptyList();
    }
}
