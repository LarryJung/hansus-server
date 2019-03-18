package com.hsmchurch.app.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SocialLoginDto {

    @JsonProperty("provider")
    private SocialProviders provider;

    @JsonProperty("token")
    private String token;

}
