package com.hsmchurch.app.noticeBoard;


import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.core.BaseEntity;
import com.hsmchurch.app.noticeBoard.form.UpdateForm;
import com.hsmchurch.app.noticeBoard.form.UploadForm;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "notices")
@Entity
public class Notice extends BaseEntity {

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
        return new Notice(updateForm.getTitle(), updateForm.getContent(), this.writer);
    }
}
