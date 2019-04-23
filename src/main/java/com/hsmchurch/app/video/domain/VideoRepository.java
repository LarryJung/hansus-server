package com.hsmchurch.app.video.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video> {

    Optional<Video> findByYoutubeId(String youtubeId);

}
