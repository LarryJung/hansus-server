package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.account.service.AccountService;
import com.hsmchurch.app.reply.QReply;
import com.hsmchurch.app.reply.Reply;
import com.hsmchurch.app.reply.ReplyRepository;
import com.hsmchurch.app.reply.dto.request.ReplyForm;
import com.hsmchurch.app.reply.dto.request.ReplyUpdateForm;
import com.hsmchurch.app.reply.dto.response.ReplyResponseDto;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.service.VideoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final AccountService accountService;
    private final VideoService videoService;
    private final JPAQueryFactory queryFactory;
    private final QReply Q_REPLY = QReply.reply;

    public ReplyResponseDto apply(final ReplyForm replyForm) {
        final Account writer = accountService.findById(replyForm.getWriterId());
        final Video targetVideo = videoService.findById(replyForm.getVideoId());
        final Reply reply = replyRepository.save(Reply.of(writer, targetVideo, replyForm.getContent()));
        return reply.toResponseDto();
    }

    public ReplyResponseDto update(final ReplyUpdateForm updateForm) {
        return replyRepository.save(findById(updateForm.getId())
                .updateContent(updateForm.getContent()))
                .toResponseDto();
    }

    public List<ReplyResponseDto> videoReplies(final Long videoId) {

        return queryFactory.select(Q_REPLY)
                .where(Q_REPLY.video.id.eq(videoId))
                .fetch().stream().map(Reply::toResponseDto)
                .collect(toList());
    }

    public void deleteReply(final Long replyId) {
        queryFactory.delete(Q_REPLY)
                .where(Q_REPLY.id.eq(replyId));
    }

    public Reply findById(final Long id) {
        return replyRepository.findById(id).orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
    }
}
