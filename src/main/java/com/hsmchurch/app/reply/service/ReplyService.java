package com.hsmchurch.app.reply.service;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.exceptions.NotFoundException;
import com.hsmchurch.app.reply.api.dto.request.ReplyDeleteRequest;
import com.hsmchurch.app.reply.api.dto.request.TargetVideoRepliesDeleteRequest;
import com.hsmchurch.app.reply.entity.Reply;
import com.hsmchurch.app.reply.entity.repository.ReplyRepository;
import com.hsmchurch.app.reply.api.dto.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.api.dto.request.ReplyUpdateRequest;
import com.hsmchurch.app.reply.api.dto.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hsmchurch.app.core.support.CrudStringFormat.*;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private static final String ENTITY_NAME = "댓글";
    private final ReplyRepository replyRepository;

    public ReplyResponse apply(final ReplyApplyRequest replyApplyRequest) {
        final Reply reply = replyRepository.save(Reply.of(replyApplyRequest));
        log.info(REGISTER_SUCCESS.apply(ENTITY_NAME), replyApplyRequest);
        return reply.toResponse();
    }

    public ReplyResponse update(final ReplyUpdateRequest replyUpdateRequest) {
        final Reply reply = replyRepository.save(findById(replyUpdateRequest.getReplyId())
                .updateContent(replyUpdateRequest.getContent(), replyUpdateRequest.getReplierId()));
        log.info(UPDATE_SUCCESS.apply(ENTITY_NAME), replyUpdateRequest);
        return reply.toResponse();
    }

    public Reply findById(final Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("댓글", id));
    }

    public List<ReplyResponse> findAllByWriter(final Long replierId) {
        return replyRepository.findAllByReplier_Id(replierId).stream()
                .map(Reply::toResponse).collect(toList());
    }

    public List<ReplyResponse> videoReplies(final Long videoId) {
        final List<Reply> replies = replyRepository.findAllByVideoId(videoId);
        if (replies.isEmpty()) {
            log.error(READ_FAIL.apply(ENTITY_NAME), videoId, "찾을 수 없습니다.");
            throw new NotFoundException(ENTITY_NAME, videoId);
        }
        return replies.stream().map(Reply::toResponse).collect(toList());
    }

    // 수정 삭제 권한에 대해서는 JWT에서 권한 필터링 처리를 할 수 있으므로 그렇게 하자. 내부에서 권한 검사를 할 경우에는 회원 서비스를 요청해야하므로..
    public boolean deleteReply(final ReplyDeleteRequest replyDeleteRequest) {
        try {
            findById(replyDeleteRequest.getReplyId()).markAsDeleted();
            log.info(DELETE_SUCCESS.apply(ENTITY_NAME), replyDeleteRequest);
            return true;
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), replyDeleteRequest, e);
            return false;
        }
    }

    public boolean deleteReplies(final TargetVideoRepliesDeleteRequest targetVideoRepliesDeleteRequest) {
        List<Reply> replies = replyRepository.findAllByVideoIdAndReplier_Id(
                targetVideoRepliesDeleteRequest.getVideoId(),
                targetVideoRepliesDeleteRequest.getReplierId());
        if (replies.isEmpty()) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), targetVideoRepliesDeleteRequest, "조회 내역이 없습니다.");
            return false;
        }
        replies.forEach(BaseEntity::markAsDeleted);
        log.info(DELETE_SUCCESS.apply(ENTITY_NAME), targetVideoRepliesDeleteRequest);
        return true;
    }
}
