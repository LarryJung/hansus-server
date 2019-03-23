package com.hsmchurch.app.reaction.entity;

import com.hsmchurch.app.core.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Table(name = "reactions")
@Entity
public class Reaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "name")
    private String name;

    public Reaction(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reaction reaction = (Reaction) o;
        return Objects.equals(id, reaction.id) &&
                Objects.equals(imageUrl, reaction.imageUrl) &&
                Objects.equals(name, reaction.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, imageUrl, name);
    }
}
