package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.entity.LikeTag;
import com.hsmchurch.app.video.entity.QLikeTag;
import com.hsmchurch.app.video.entity.repository.LikeTagRepository;
import com.hsmchurch.app.video.api.dto.request.LikeTagForm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeTagService {

    private final LikeTagRepository likeTagRepository;
    private final static QLikeTag Q_LIKE_TAG = QLikeTag.likeTag;
    @PersistenceContext
    private EntityManager entityManager;
    private JPAQueryFactory queryFactory =  new JPAQueryFactory(entityManager);

    public void applyLikeTag(final LikeTagForm likeTagForm) {

        likeTagRepository.save(LikeTag.of(likeTagForm));
    }

    public long cancelLikeTag(final LikeTagForm likeTagForm) {
        return queryFactory.delete(Q_LIKE_TAG)
                .where(
                        Q_LIKE_TAG.videoId.eq(likeTagForm.getVideoId())
                                .and(Q_LIKE_TAG.accountId.eq(likeTagForm.getAccountId()))
                )
                .execute();
    }

    public List<LikeTag> findAllByAccountId(final Long accountId) {
        return likeTagRepository.findAllByAccountId(accountId);
    }
}
