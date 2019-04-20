package com.hsmchurch.app.video.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface LikeTagRepository :
        JpaRepository<LikeTag, LikeTagId>,
        QuerydslPredicateExecutor<LikeTag>,
        LikeTagRepositoryCustom {

    fun findAllByLikeTagId_AccountId(accountId: Long, pageable: Pageable): Page<LikeTag>

    fun findByLikeTagId(likeTagId: LikeTagId): LikeTag?

    fun findAllByLikeTagId_VideoId(videoId: Long): List<LikeTag>

}
