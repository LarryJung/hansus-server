package com.hsmchurch.app.video.application;

import com.hsmchurch.app.common.exceptions.NotFoundException;
import com.hsmchurch.app.video.domain.LikeTag;
import com.hsmchurch.app.video.domain.LikeTagId;
import com.hsmchurch.app.video.domain.LikeTagRepository;
import com.hsmchurch.app.video.ui.request.LikeTagRequest;
import com.hsmchurch.app.video.ui.response.LikeUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hsmchurch.app.common.support.CrudStringFormat.CANCEL_FAIL;

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

    public Page<LikeTag> findAllByAccountId(final Long accountId,
                                            final Pageable pageable) {
        return likeTagRepository.findAllByLikeTagId_AccountId(accountId, pageable);
    }

    public LikeTag findBy(final Long videoId,
                          final Long accountId) {
        final LikeTagId id = new LikeTagId(videoId, accountId);

        return likeTagRepository.findByLikeTagId(id)
                .orElseThrow(() -> new NotFoundException("좋아요 태그", id));
    }

    public List<LikeTag> findByVideoId(final Long videoId) {
        return likeTagRepository.findAllByLikeTagId_VideoId(videoId);
    }

    public List<LikeUser> findAllByVideo(final Long videoId) {
        return likeTagRepository.findAllByVideo(videoId);
    }
}
