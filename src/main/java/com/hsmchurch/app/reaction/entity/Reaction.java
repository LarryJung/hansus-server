package com.hsmchurch.app.reaction.entity;

import com.hsmchurch.app.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "reactions")
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Reaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    private String name;

    public Reaction(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
