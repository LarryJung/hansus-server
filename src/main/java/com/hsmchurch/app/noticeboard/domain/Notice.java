package com.hsmchurch.app.noticeboard.domain;

import com.hsmchurch.app.common.BaseEntity;
import com.hsmchurch.app.common.Feedable;
import com.hsmchurch.app.common.HasOwner;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.ui.request.NoticeUploadRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Slf4j
@Getter
@NoArgsConstructor
@Table(name = "notices")
@Entity
public class Notice extends BaseEntity implements HasOwner, Feedable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content; // 단순 글이 아닐 것이므로 향후 객체화 가능

    @Embedded
    private Writer writer;

    public Notice(final String title,
                  final String content,
                  final Writer writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Notice of(final NoticeUploadRequest noticeUploadRequest,
                            final Long writerId) {
        return new Notice(noticeUploadRequest.getTitle(), noticeUploadRequest.getContent(), new Writer(writerId));
    }

    public Notice update(final NoticeUpdateRequest noticeUpdateRequest,
                         final Long writerId) {
        if (!isOwner(writerId)) {
            throw new RuntimeException("올바른 작성자가 아닙니다. writer id: " + writerId);
        }
        return new Notice(noticeUpdateRequest.getTitle(), noticeUpdateRequest.getContent(), this.writer);
    }

    @Override
    public boolean isOwner(final Long writerId) {
        return this.writer.isYou(writerId);
    }

    public boolean checkAndDelete(final Long writerId) {
        if (isOwner(writerId)) {
            markAsDeleted();
            return true;
        }
        return false;
    }

    @Override
    public LocalDateTime feed_created_at() {
        return getCreatedAt();
    }
}
