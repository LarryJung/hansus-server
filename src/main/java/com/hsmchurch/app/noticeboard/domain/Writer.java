package com.hsmchurch.app.noticeboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Embeddable
public class Writer {

    @Column(name = "writer_id")
    private Long id;

    public Writer(final Long writerId) {
        this.id = writerId;
    }

    public boolean isYou(final Long writerId) {
        return writerId.equals(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(id, writer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
