package com.hsmchurch.app.noticeBoard.entity;


import com.hsmchurch.app.account.entity.Account;
import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.core.hasOwner;
import com.hsmchurch.app.noticeBoard.dto.request.UpdateForm;
import com.hsmchurch.app.noticeBoard.dto.request.UploadForm;
import com.hsmchurch.app.noticeBoard.dto.response.NoticeResponseDto;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "notices")
@Entity
public class Notice extends BaseEntity implements hasOwner {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content; // 단순 글이 아닐 것이므로 향후 객체화 가능

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private Account writer;

    public Notice(final String title, final String content, final Account writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Notice of(final Account writer, final UploadForm uploadForm) {
        return new Notice(uploadForm.getTitle(), uploadForm.getContent(), writer);
    }

    public Notice update(final UpdateForm updateForm) {
        if (!isOwner(updateForm.getWriterId())) {
            throw new RuntimeException("올바른 작성자가 아닙니다. writer id: " + updateForm.getWriterId());
        }
        return new Notice(updateForm.getTitle(), updateForm.getContent(), this.writer);
    }

    @Override
    public boolean isOwner(Long writerId) {
        return this.writer.isYou(writerId);
    }

    public NoticeResponseDto toResponseDto() {
        return NoticeResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer.toResponseDto())
                .build();
    }
}
