package com.hsmchurch.app.account.domain;

import com.hsmchurch.app.account.ui.response.AccountResponse;
import com.hsmchurch.app.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "account_origin")
    @Enumerated(EnumType.STRING)
    private AccountOrigin accountOrigin;

    @Column(name = "social_id")
    private String socialId = null;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "gender")
    private String gender = null;

    @Column(name = "birthday")
    private String birthday = null;

    @Column(name = "age_range")
    private String ageRange = null;

    public boolean isYou(final Long writerId) {
        return id.equals(writerId);
    }

    public AccountResponse toResponse() {
        return AccountResponse.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .accountOrigin(this.accountOrigin)
                .gender(this.gender)
                .birthday(this.birthday)
                .build();
    }

}
