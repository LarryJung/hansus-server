package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.video.domain.LikeTag;
import com.hsmchurch.app.video.domain.LikeTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface LikeTagRepository extends JpaRepository<LikeTag, LikeTagId>, QuerydslPredicateExecutor<LikeTag> {

    List<LikeTag> findAllByLikeTagId_AccountId(Long accountId);

    Optional<LikeTag> findByLikeTagId(LikeTagId likeTagId);
}
