package com.hsmchurch.app.account.ui.response;

import com.hsmchurch.app.account.domain.Role;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountResponse {

    private final Long id;
    private final String name;
    private final Role role;

}
