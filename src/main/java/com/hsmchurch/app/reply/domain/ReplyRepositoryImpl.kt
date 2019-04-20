package com.hsmchurch.app.reply.domain

import com.hsmchurch.app.account.domain.QAccount
import com.hsmchurch.app.video.ui.response.ReplyForVideo
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.util.Collections.emptyList

@Repository
@Transactional
open class ReplyRepositoryImpl(private val queryFactory: JPAQueryFactory) : QuerydslRepositorySupport(Reply::class.java), ReplyRepositoryCustom {

    override fun videoReplies(videoId: Long): List<ReplyForVideo> {
        val result = queryFactory.from(reply)
                .select(Projections.fields(ReplyForVideo::class.java,
                        reply.id.`as`("id"),
                        reply.content.`as`("content"),
                        reply.replier.id.`as`("replierId"),
                        account.name.`as`("replierName"),
                        reply.createdAt.`as`("createdAt"),
                        reply.updatedAt.`as`("updatedAt"))
                )
                .join(reply).on(reply.replier.id.eq(account.id))
                .where(reply.videoId.eq(videoId))
                .fetch()

        return result ?: emptyList()
    }

    companion object {

        private val reply = QReply.reply
        private val account = QAccount.account
    }
}
