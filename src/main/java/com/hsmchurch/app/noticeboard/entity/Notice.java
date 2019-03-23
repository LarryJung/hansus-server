package com.hsmchurch.app.noticeboard.entity;

import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.hasOwner;
import com.hsmchurch.app.noticeboard.dto.request.NoticeUpdateRequest;
import com.hsmchurch.app.noticeboard.dto.request.NoticeUploadRequest;
import com.hsmchurch.app.noticeboard.dto.response.NoticeResponse;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Table(name = "notices")
@Entity
public class Notice extends BaseEntity implements hasOwner {

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

    public static Notice of(final NoticeUploadRequest noticeUploadRequest) {
        return new Notice(noticeUploadRequest.getTitle(), noticeUploadRequest.getContent(), new Writer(noticeUploadRequest.getWriterId(), noticeUploadRequest.getWriterName()));
    }

    public Notice update(final NoticeUpdateRequest noticeUpdateRequest) {
        if (!isOwner(noticeUpdateRequest.getWriterId())) {
            throw new RuntimeException("올바른 작성자가 아닙니다. writer id: " + noticeUpdateRequest.getWriterId());
        }
        return new Notice(noticeUpdateRequest.getTitle(), noticeUpdateRequest.getContent(), this.writer);
    }

    @Override
    public boolean isOwner(final Long writerId) {
        return this.writer.isYou(writerId);
    }

    public NoticeResponse toResponse() {
        return NoticeResponse.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }
}
