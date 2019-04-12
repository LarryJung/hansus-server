package com.hsmchurch.app.noticeboard.application;

import com.hsmchurch.app.common.exceptions.NotFoundException;
import com.hsmchurch.app.noticeboard.domain.Notice;
import com.hsmchurch.app.noticeboard.domain.NoticeRepository;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.ui.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.hsmchurch.app.common.support.CrudStringFormat.DELETE_FAIL;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final static String ENTITY_NAME = "게시글";
    private final NoticeRepository noticeRepository;

    public Notice upload(final NoticeUploadRequest noticeUploadRequest,
                         final Long writerId) {
        return noticeRepository.save(Notice.of(noticeUploadRequest, writerId));
    }

    public Notice findById(final Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NotFoundException(ENTITY_NAME, noticeId));
    }

    public NoticeResponse findById2(final Long noticeId) {
        return noticeRepository.findOneWithWriterName(noticeId);
    }

    public Notice updateNotice(final NoticeUpdateRequest noticeUpdateRequest,
                               final Long boardId,
                               final Long writerId) {
        final Notice notice = findById(boardId).update(noticeUpdateRequest, writerId);
        return noticeRepository.save(notice);
    }

    public boolean delete(final Long boardId) {
        try {
            findById(boardId).markAsDeleted();
            return true;
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), boardId, e);
            return false;
        }
    }

    public boolean delete(final Long boardId,
                          final Long writerId) {
        try {
            return findById(boardId).checkAndDelete(writerId);
        } catch (NotFoundException e) {
            log.error(DELETE_FAIL.apply(ENTITY_NAME), boardId, e);
            return false;
        }
    }

    public Page<NoticeResponse> findListAll(final Pageable pageable) {
        return noticeRepository.findListAll(pageable);
    }

}
