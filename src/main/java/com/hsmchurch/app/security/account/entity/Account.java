package com.hsmchurch.app.security.account.entity;

import com.hsmchurch.app.security.account.api.dto.request.AccountResponseDto;
import com.hsmchurch.app.security.account.entity.value.AccountOrigin;
import com.hsmchurch.app.security.account.entity.value.Role;
import com.hsmchurch.app.core.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "accounts")
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountOrigin accountOrigin;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Account of(Long id, String name, String password, AccountOrigin accountOrigin, Role role) {
        return new Account(id, name, password, accountOrigin, role);
    }

    public boolean isYou(final Long writerId) {
        return id.equals(writerId);
    }

    public AccountResponseDto toResponseDto() {
        return AccountResponseDto.builder()
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
}
