package com.hsmchurch.app.reaction.service;

import com.hsmchurch.app.core.exceptions.NotFoundException;
import com.hsmchurch.app.reaction.entity.*;
import com.hsmchurch.app.video.api.dto.request.ReactionApplyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.hsmchurch.app.core.support.CrudStringFormat.DELETE_FAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReactionService {
    private final static String ENTITY_NAME_REACTION = "리액션";
    private final static String ENTITY_NAME_REACTION_HISTORY = "리액션";
    private final ReactionRepository reactionRepository;
    private final ReactionHistoryRepository reactionHistoryRepository;

    public Reaction findReactionById(final Long reactionId) {
        return reactionRepository.findById(reactionId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME_REACTION, reactionId));
    }

    public ReactionHistory findReactionHistory(final ReactionHistoryId reactionHistoryId) {
        return reactionHistoryRepository.findByReactionHistoryId(reactionHistoryId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME_REACTION_HISTORY, reactionHistoryId));
    }

    public ReactionHistory applyReaction(final ReactionApplyRequest reactionApplyRequest) {
        return reactionHistoryRepository.save(ReactionHistory.of(reactionApplyRequest));
    }

    public boolean cancelReaction(final ReactionApplyRequest reactionCancelRequest) {
        final ReactionHistoryId reactionHistoryId = new ReactionHistoryId(
                reactionCancelRequest.getAccountId(),
                reactionCancelRequest.getVideoId(),
                reactionCancelRequest.getReactionId());
        try {
            findReactionHistory(reactionHistoryId).cancel();
            return true;
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME_REACTION_HISTORY), reactionCancelRequest, e);
            return false;
        }
    }

}
