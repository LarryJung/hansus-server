package com.hsmchurch.app.video.service;

import com.hsmchurch.app.video.entity.LikeTag;
import com.hsmchurch.app.video.entity.repository.LikeTagRepository;
import com.hsmchurch.app.video.api.dto.request.LikeTagForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeTagService {

    private final LikeTagRepository likeTagRepository;

    public void applyLikeTag(final LikeTagForm likeTagForm) {

        likeTagRepository.save(LikeTag.of(likeTagForm));
    }

    public boolean cancelLikeTag(final LikeTagForm likeTagForm) {
        final LikeTag willBeDeleted = likeTagRepository.findByVideoIdAndAccountId(likeTagForm.getVideoId(), likeTagForm.getUserId())
                .orElseThrow(() -> new RuntimeException("좋아요 태그를 찾을 수 없습니다."));
        try {
            likeTagRepository.delete(willBeDeleted);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<LikeTag> findAllByAccountId(Long accountId) {
        return likeTagRepository.findAllByAccountId(accountId);
    }
}
