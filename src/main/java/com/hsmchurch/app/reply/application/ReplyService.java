package com.hsmchurch.app.reply.application;

import com.hsmchurch.app.common.BaseEntity;
import com.hsmchurch.app.common.exceptions.NotFoundException;
import com.hsmchurch.app.reply.domain.Reply;
import com.hsmchurch.app.reply.domain.ReplyRepository;
import com.hsmchurch.app.reply.ui.request.ReplyApplyRequest;
import com.hsmchurch.app.reply.ui.request.ReplyUpdateRequest;
import com.hsmchurch.app.reply.ui.response.ReplyResponse;
import com.hsmchurch.app.video.ui.response.ReplyForVideo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hsmchurch.app.common.support.CrudStringFormat.*;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private static final String ENTITY_NAME = "댓글";
    private final ReplyRepository replyRepository;

    public Reply apply(final ReplyApplyRequest replyApplyRequest,
                       final Long replierId) {
        final Reply reply = replyRepository.save(Reply.of(replyApplyRequest, replierId));
        log.info(REGISTER_SUCCESS.apply(ENTITY_NAME), replyApplyRequest);
        return reply;
    }

    public Reply update(final ReplyUpdateRequest replyUpdateRequest,
                        final Long replyId,
                        final Long replierId) {
        final Reply reply = replyRepository.save(findById(replyId)
                .updateContent(replyUpdateRequest.getContent(), replierId));
        log.info(UPDATE_SUCCESS.apply(ENTITY_NAME), replyUpdateRequest);
        return reply;
    }

    public Reply findById(final Long id) {
        return replyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("댓글", id));
    }

    //    public List<ReplyResponse> findAllByWriter(final Long replierId) {
//        return replyRepository.findAllByReplier_Id(replierId).stream()
//                .map(Reply::toResponse).collect(toList());
//    }
//
    public Page<Reply> findAllByWriter(final Long replierId,
                                       final Pageable pageable) {
        return replyRepository.findAllByReplier_Id(replierId, pageable);
    }

    public List<ReplyResponse> videoReplies(final Long videoId) {
        final List<Reply> replies = replyRepository.findAllByVideoId(videoId);
        if (replies.isEmpty()) {
            log.error(READ_FAIL.apply(ENTITY_NAME), videoId, "찾을 수 없습니다.");
            throw new NotFoundException(ENTITY_NAME, videoId);
        }
        return replies.stream().map(Reply::toResponse).collect(toList());
    }

    public boolean forceDeleteReply(final Long replyId) {
        try {
            findById(replyId).markAsDeleted();
            log.info(DELETE_SUCCESS.apply(ENTITY_NAME), replyId);
            return true;
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), replyId, e);
            return false;
        }
    }

    public boolean checkDeleteReply(final Long replyId,
                                    final Long replierId) {
        try {
            return findById(replyId).checkAndDelete(replierId);
        } catch (NotFoundException e) {
            return false;
        }
    }

    public boolean deleteReplies(final Long videoId,
                                 final Long replierId) {
        final List<Reply> replies = replyRepository.findAllByVideoIdAndReplier_Id(videoId, replierId);
        if (replies.isEmpty()) {
            return false;
        }
        replies.forEach(BaseEntity::markAsDeleted);
        return true;
    }

    public List<Reply> findAllByVideo(final Long videoId) {
        return replyRepository.findAllByVideoId(videoId);
    }

    public List<ReplyForVideo> findAllByVideo2(final Long videoId) {
        return replyRepository.videoReplies(videoId);
    }
}
