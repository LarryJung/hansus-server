package com.hsmchurch.app.reaction.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ReactionHistoryRepository extends JpaRepository<ReactionHistory, ReactionHistoryId>, QuerydslPredicateExecutor<ReactionHistory> {
    Optional<ReactionHistory> findByReactionHistoryId(ReactionHistoryId reactionHistoryId);
}
