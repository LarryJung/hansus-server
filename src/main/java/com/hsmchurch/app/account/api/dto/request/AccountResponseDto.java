package com.hsmchurch.app.account.api.dto.request;

import com.hsmchurch.app.account.entity.value.Role;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountResponseDto {

    private Long id;
    private String name;
    private Role role;

}
