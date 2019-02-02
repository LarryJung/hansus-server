package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.account.entity.Account;
import com.hsmchurch.app.account.service.AccountService;
import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteForm;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteForm;
import com.hsmchurch.app.reply.entity.QReply;
import com.hsmchurch.app.reply.entity.Reply;
import com.hsmchurch.app.reply.entity.ReplyRepository;
import com.hsmchurch.app.reply.api.dto.request.ReplyApplyForm;
import com.hsmchurch.app.reply.api.dto.request.ReplyUpdateForm;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponseDto;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.service.VideoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final AccountService accountService;
    private final VideoService videoService;
    private final JPAQueryFactory queryFactory;

    private static final QReply Q_REPLY = QReply.reply;

    public ReplyResponseDto apply(final ReplyApplyForm replyApplyForm) {
        final Account writer = accountService.findById(replyApplyForm.getWriterId());
        final Video targetVideo = videoService.findById(replyApplyForm.getVideoId());
        final Reply reply = replyRepository.save(Reply.of(writer, targetVideo, replyApplyForm.getContent()));
        return reply.toResponseDto();
    }

    public ReplyResponseDto update(final ReplyUpdateForm updateForm) {
        return replyRepository.save(findById(updateForm.getReplyId())
                .updateContent(updateForm.getContent(), updateForm.getWriterId()))
                .toResponseDto();
    }

    public List<ReplyResponseDto> videoReplies(final Long videoId) {
        return queryFactory.select(Q_REPLY)
                .where(Q_REPLY.video.id.eq(videoId))
                .fetch().stream().map(Reply::toResponseDto)
                .collect(toList());
    }

    @Transactional
    public long deleteReply(final ReplyDeleteForm deleteForm) {
        return queryFactory.delete(Q_REPLY)
                .where(
                        Q_REPLY.id.eq(deleteForm.getReplyId())
                                .and(Q_REPLY.writer.id.eq(deleteForm.getWriterId()))
                )
                .execute();
    }

    @Transactional
    public long deleteReplies(final TargetVideoRepliesDeleteForm deleteForm) {
        return queryFactory.delete(Q_REPLY)
                .where(
                        Q_REPLY.video.id.eq(deleteForm.getVideoId())
                                .and(Q_REPLY.writer.id.eq(deleteForm.getWriterId()))
                )
                .execute();
    }

    public Reply findById(final Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
    }

    public List<ReplyResponseDto> findAllByWriter(final Long writerId) {
        return replyRepository.findAllByWriterId(writerId).stream()
                .map(Reply::toResponseDto)
                .collect(toList());
    }
}
