package com.hsmchurch.app.account.api.dto.response;

import com.hsmchurch.app.account.entity.value.Role;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountResponse {

    private final Long id;
    private final String name;
    private final Role role;

}
