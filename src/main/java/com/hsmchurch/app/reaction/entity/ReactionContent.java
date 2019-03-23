package com.hsmchurch.app.reaction.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor
public class ReactionContent {

    @Column(name = "reaction_image_url")
    private String imageUrl;

    @Column(name = "reaction_name")
    private String name;

    public ReactionContent(final String imageUrl, final String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionContent content = (ReactionContent) o;
        return Objects.equals(imageUrl, content.imageUrl) &&
                Objects.equals(name, content.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, name);
    }
}
