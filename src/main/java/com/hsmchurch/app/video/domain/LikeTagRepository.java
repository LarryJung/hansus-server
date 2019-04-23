package com.hsmchurch.app.video.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface LikeTagRepository extends
        JpaRepository<LikeTag, LikeTagId>,
        QuerydslPredicateExecutor<LikeTag>,
        LikeTagRepositoryCustom {

    List<LikeTag> findAllByLikeTagId_AccountId(Long accountId);

    Optional<LikeTag> findByLikeTagId(LikeTagId likeTagId);

    List<LikeTag> findAllByLikeTagId_VideoId(Long videoId);

}
