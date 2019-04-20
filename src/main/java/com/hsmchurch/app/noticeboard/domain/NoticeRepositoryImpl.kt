package com.hsmchurch.app.noticeboard.domain

import com.hsmchurch.app.account.domain.QAccount
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse
import com.hsmchurch.app.reply.domain.Reply
import com.querydsl.core.types.Projections
import com.querydsl.core.types.QBean
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
open class NoticeRepositoryImpl(
        private val queryFactory: JPAQueryFactory
) : QuerydslRepositorySupport(Reply::class.java), NoticeRepositoryCustom {

    override fun findListAll(pageable: Pageable): Page<NoticeResponse?> {
        val queryResults = queryFactory.from(notice)
                .select(qNoticeResponse())
                .join(notice).on(notice.writer.id.eq(account.id))
                .limit(pageable.pageSize.toLong())
                .offset(pageable.offset)
                .fetchResults()
        return PageImpl(queryResults.results, pageable, queryResults.total)
    }

    override fun findOneWithWriterName(noticeId: Long): NoticeResponse? {
        return queryFactory.from(notice)
                .select(qNoticeResponse())
                .join(notice).on(notice.writer.id.eq(account.id))
                .where(notice.id.eq(noticeId!!))
                .fetchOne()
    }

    companion object {

        private val notice = QNotice.notice
        private val account = QAccount.account

        private fun qNoticeResponse(): QBean<NoticeResponse> {
            return Projections.fields(NoticeResponse::class.java,
                    notice.id.`as`("id"),
                    notice.title.`as`("title"),
                    notice.content.`as`("content"),
                    account.id.`as`("writerId"),
                    account.name.`as`("name"),
                    notice.createdAt.`as`("createdAt"),
                    notice.updatedAt.`as`("updatedAt")
            )
        }
    }
}
