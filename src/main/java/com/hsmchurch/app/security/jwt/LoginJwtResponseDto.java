package com.hsmchurch.app.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginJwtResponseDto {

    @JsonProperty(value = "token")
    private String token;

    public LoginJwtResponseDto (String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
