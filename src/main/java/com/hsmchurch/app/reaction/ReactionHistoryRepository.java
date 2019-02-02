package com.hsmchurch.app.reaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ReactionHistoryRepository extends JpaRepository<ReactionHistory, Long>, QuerydslPredicateExecutor<ReactionHistory> {

}
