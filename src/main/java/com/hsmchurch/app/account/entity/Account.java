package com.hsmchurch.app.account.entity;

import com.hsmchurch.app.account.api.dto.response.AccountResponse;
import com.hsmchurch.app.account.entity.value.AccountOrigin;
import com.hsmchurch.app.account.entity.value.Role;
import com.hsmchurch.app.core.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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
    private String socialId;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "age_range")
    private String ageRange;

    public boolean isYou(final Long writerId) {
        return id.equals(writerId);
    }

    public AccountResponse toResponse() {
        return AccountResponse.builder()
                .id(this.id)
                .name(this.name)
                .role(this.role)
                .build();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accountOrigin=" + accountOrigin +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(password, account.password) &&
                accountOrigin == account.accountOrigin &&
                Objects.equals(socialId, account.socialId) &&
                role == account.role &&
                Objects.equals(gender, account.gender) &&
                Objects.equals(birthday, account.birthday) &&
                Objects.equals(ageRange, account.ageRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, password, accountOrigin, socialId, role, gender, birthday, ageRange);
    }
}
