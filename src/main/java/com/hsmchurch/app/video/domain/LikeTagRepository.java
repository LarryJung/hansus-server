package com.hsmchurch.app.video.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface LikeTagRepository extends JpaRepository<LikeTag, LikeTagId>, QuerydslPredicateExecutor<LikeTag> {

    Page<LikeTag> findAllByLikeTagId_AccountId(Long accountId, Pageable pageable);

    Optional<LikeTag> findByLikeTagId(LikeTagId likeTagId);
}
