package com.hsmchurch.app.noticeboard.service;

import com.hsmchurch.app.common.exceptions.NotFoundException;
import com.hsmchurch.app.noticeboard.entity.Notice;
import com.hsmchurch.app.noticeboard.entity.NoticeRepository;
import com.hsmchurch.app.noticeboard.dto.request.NoticeDeleteRequest;
import com.hsmchurch.app.noticeboard.dto.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.dto.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.dto.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.hsmchurch.app.common.support.CrudStringFormat.DELETE_FAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final static String ENTITY_NAME = "게시글";
    private final NoticeRepository noticeRepository;

    public NoticeResponse upload(final NoticeUploadRequest noticeUploadRequest) {
        return noticeRepository.save(Notice.of(noticeUploadRequest)).toResponse();
    }

    public Notice findById(final Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME, noticeId));
    }

    public NoticeResponse updateNotice(final NoticeUpdateRequest noticeUpdateRequest) {
        final Notice notice = findById(noticeUpdateRequest.getBoardId()).update(noticeUpdateRequest);
        return noticeRepository.save(notice).toResponse();
    }

    public boolean delete(final NoticeDeleteRequest noticeDeleteRequest) {
        try {
            findById(noticeDeleteRequest.getBoardId()).markAsDeleted();
            return true;
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), noticeDeleteRequest, e);
            return false;
        }
    }
}
