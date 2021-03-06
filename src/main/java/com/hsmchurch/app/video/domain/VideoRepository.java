package com.hsmchurch.app.video.domain;

import com.hsmchurch.app.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {
}
