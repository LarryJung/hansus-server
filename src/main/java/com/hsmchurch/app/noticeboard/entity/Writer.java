package com.hsmchurch.app.noticeboard.entity;

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

    @Column(name = "writer_name")
    private String name;

    public Writer(final Long writerId,
                  final String name) {
        this.id = writerId;
        this.name = name;
    }

    public boolean isYou(final Long writerId) {
        return writerId.equals(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(id, writer.id) &&
                Objects.equals(name, writer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
