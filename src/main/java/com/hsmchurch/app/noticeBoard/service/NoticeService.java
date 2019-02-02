package com.hsmchurch.app.noticeBoard.service;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.noticeBoard.Notice;
import com.hsmchurch.app.noticeBoard.NoticeRepository;
import com.hsmchurch.app.noticeBoard.form.UpdateForm;
import com.hsmchurch.app.noticeBoard.form.UploadForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice upload(final Account writer, final UploadForm uploadForm) {
        return noticeRepository.save(Notice.of(writer, uploadForm));
    }

    public Notice read(final Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new RuntimeException("Notice를 찾을 수 없습니다."));
    }

    public Notice updateNotice(final UpdateForm updateForm) {
        final Notice notice = read(updateForm.getId()).update(updateForm);
        return noticeRepository.save(notice);
    }

    public boolean delete(final Long noticeId) {
        try {
            noticeRepository.deleteById(noticeId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
