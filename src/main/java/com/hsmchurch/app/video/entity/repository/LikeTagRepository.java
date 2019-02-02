package com.hsmchurch.app.video.entity.repository;

import com.hsmchurch.app.video.entity.LikeTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface LikeTagRepository extends JpaRepository<LikeTag, Long>, QuerydslPredicateExecutor<LikeTag> {

    List<LikeTag> findAllByAccountId(Long accountId);

    Optional<LikeTag> findByVideoIdAndAccountId(Long videoId, Long accountId);

}
