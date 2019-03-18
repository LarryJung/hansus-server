package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.security.account.entity.Account;
import com.hsmchurch.app.security.account.service.AccountService;
import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteForm;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteForm;
import com.hsmchurch.app.reply.entity.Reply;
import com.hsmchurch.app.reply.entity.repository.ReplyRepository;
import com.hsmchurch.app.reply.api.dto.request.ReplyApplyForm;
import com.hsmchurch.app.reply.api.dto.request.ReplyUpdateForm;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponseDto;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final AccountService accountService;
    private final VideoService videoService;

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

    public Reply findById(final Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
    }

    public List<ReplyResponseDto> findAllByWriter(final Long writerId) {
        return replyRepository.findAllByWriterId(writerId).stream()
                .map(Reply::toResponseDto)
                .collect(toList());
    }

    public List<ReplyResponseDto> videoReplies(final Long videoId) {
        return replyRepository.videoReplies(videoId);
    }

    public long deleteReply(final ReplyDeleteForm deleteForm) {
        return replyRepository.deleteReply(deleteForm);
    }

    public long deleteReplies(final TargetVideoRepliesDeleteForm deleteForm) {
        return replyRepository.deleteReplies(deleteForm);
    }
}
