package com.hsmchurch.app.video.service;

import com.hsmchurch.app.core.exceptions.NotFoundException;
import com.hsmchurch.app.video.entity.LikeTag;
import com.hsmchurch.app.video.entity.LikeTagId;
import com.hsmchurch.app.video.entity.repository.LikeTagRepository;
import com.hsmchurch.app.video.api.dto.request.LikeTagRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hsmchurch.app.core.support.CrudStringFormat.CANCEL_FAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeTagService {

    private final static String ENTITY_NAME = "좋아요 태그";
    private final LikeTagRepository likeTagRepository;

    public void applyLikeTag(final LikeTagRequest likeTagRequest) {
        likeTagRepository.save(LikeTag.of(likeTagRequest));
    }

    public boolean cancelLikeTag(final LikeTagRequest likeTagRequest) {
        try {
            findBy(likeTagRequest.getVideoId(), likeTagRequest.getAccountId()).cancel();
            return true;
        } catch (NotFoundException e) {
            log.error(CANCEL_FAIL.apply(ENTITY_NAME), likeTagRequest, e);
            return false;
        }
    }

    public List<LikeTag> findAllByAccountId(final Long accountId) {
        return likeTagRepository.findAllByLikeTagId_AccountId(accountId);
    }

    public LikeTag findBy(final Long videoId, final Long accountId) {
        final LikeTagId id = new LikeTagId(videoId, accountId);
        return likeTagRepository.findByLikeTagId(id)
                .orElseThrow(() -> new NotFoundException("좋아요 태그", id));
    }
}
