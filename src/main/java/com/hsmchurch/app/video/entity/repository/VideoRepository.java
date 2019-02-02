package com.hsmchurch.app.video.entity.repository;

import com.hsmchurch.app.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface VideoRepository extends JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {
}
