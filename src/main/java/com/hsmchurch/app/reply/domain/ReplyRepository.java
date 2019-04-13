package com.hsmchurch.app.reply.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {

    Page<Reply> findAllByReplier_Id(Long writerId, Pageable pageable);

    List<Reply> findAllByVideoIdAndReplier_Id(Long videoId, Long replierId);

    List<Reply> findAllByVideoId(Long videoId);

}
