package com.hsmchurch.app.reply.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class Replier {

    @Column(name = "replier_id")
    private Long id;

    @Column(name = "replier_name")
    private String name;

    public Replier(final Long id,
                   final String name) {
        this.id = id;
        this.name = name;
    }

    public boolean isYou(final Long replierId) {
        return this.id.equals(replierId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Replier replier = (Replier) o;
        return Objects.equals(id, replier.id) &&
                Objects.equals(name, replier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
