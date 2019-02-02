package com.hsmchurch.app.reaction.service;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.account.service.AccountService;
import com.hsmchurch.app.reaction.*;
import com.hsmchurch.app.video.entity.Video;
import com.hsmchurch.app.video.api.dto.request.ReactionApplyForm;
import com.hsmchurch.app.video.service.VideoService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final ReactionHistoryRepository reactionHistoryRepository;
    private final VideoService videoService;
    private final AccountService accountService;
    private final JPAQueryFactory queryFactory;

    public Reaction findById(Long reactionId) {
        return reactionRepository.findById(reactionId)
                .orElseThrow(() -> new RuntimeException("리액션을 찾을 수 없습니다."));
    }

    public void applyReaction(final ReactionApplyForm applyForm) {
        final Account account = accountService.findById(applyForm.getUserId());
        final Video video = videoService.findById(applyForm.getVideoId());
        final Reaction reaction = findById(applyForm.getReactionId());
        reactionHistoryRepository.save(ReactionHistory.of(reaction, account, video));
    }

    public void cancelReaction(final ReactionApplyForm applyForm) {
        QReactionHistory reactionHistory = QReactionHistory.reactionHistory;
        queryFactory.delete(reactionHistory)
                .where(
                        reactionHistory.video.id.eq(applyForm.getVideoId())
                                .and(reactionHistory.account.id.eq(applyForm.getUserId()))
                                .and(reactionHistory.reaction.id.eq(applyForm.getReactionId()))
                );
    }
}