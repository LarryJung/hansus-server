package com.hsmchurch.app.reply.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository : JpaRepository<Reply, Long>, ReplyRepositoryCustom {

    fun findAllByReplier_Id(writerId: Long, pageable: Pageable): Page<Reply>

    fun findAllByVideoIdAndReplier_Id(videoId: Long, replierId: Long): List<Reply>

}
