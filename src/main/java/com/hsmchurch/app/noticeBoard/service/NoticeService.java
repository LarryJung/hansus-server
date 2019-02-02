package com.hsmchurch.app.noticeBoard.service;

import com.hsmchurch.app.account.entity.Account;
import com.hsmchurch.app.noticeBoard.entity.Notice;
import com.hsmchurch.app.noticeBoard.entity.NoticeRepository;
import com.hsmchurch.app.noticeBoard.dto.request.DeleteForm;
import com.hsmchurch.app.noticeBoard.dto.request.UpdateForm;
import com.hsmchurch.app.noticeBoard.dto.request.UploadForm;
import com.hsmchurch.app.noticeBoard.dto.response.NoticeResponseDto;
import com.hsmchurch.app.noticeBoard.entity.QNotice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final JPAQueryFactory queryFactory;
    private static final QNotice Q_NOTICE = QNotice.notice;

    public NoticeResponseDto upload(final Account writer, final UploadForm uploadForm) {
        return noticeRepository.save(Notice.of(writer, uploadForm)).toResponseDto();
    }

    public Notice findById(final Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Notice를 찾을 수 없습니다."));
    }

    public NoticeResponseDto updateNotice(final UpdateForm updateForm) {
        final Notice notice = findById(updateForm.getBoardId()).update(updateForm);
        return noticeRepository.save(notice)
                .toResponseDto();
    }

    public long delete(final DeleteForm deleteForm) {
        return queryFactory.delete(Q_NOTICE)
                .where(Q_NOTICE.id.eq(deleteForm.getBoardId())
                        .and(Q_NOTICE.writer.id.eq(deleteForm.getWriterId())))
                .execute();
    }
}
