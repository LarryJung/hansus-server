package com.hsmchurch.app.noticeboard.application;

import com.hsmchurch.app.common.exceptions.NotFoundException;
import com.hsmchurch.app.noticeboard.domain.Notice;
import com.hsmchurch.app.noticeboard.domain.NoticeRepository;
import com.hsmchurch.app.noticeboard.ui.request.NoticeDeleteRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.hsmchurch.app.common.support.CrudStringFormat.DELETE_FAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final static String ENTITY_NAME = "게시글";
    private final NoticeRepository noticeRepository;

    public Notice upload(final NoticeUploadRequest noticeUploadRequest) {
        return noticeRepository.save(Notice.of(noticeUploadRequest));
    }

    public Notice findById(final Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME, noticeId));
    }

    public Notice updateNotice(final NoticeUpdateRequest noticeUpdateRequest) {
        final Notice notice = findById(noticeUpdateRequest.getBoardId()).update(noticeUpdateRequest);
        return noticeRepository.save(notice);
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

    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }
}
