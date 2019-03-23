package com.hsmchurch.app.reply.entity.repository;

import com.hsmchurch.app.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyRepositoryCustom {

    List<Reply> findAllByReplier_Id(Long writerId);

    List<Reply> findAllByVideoIdAndReplier_Id(Long videoId, Long replierId);

}
