package com.hsmchurch.app.security.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class KakaoUserProperty implements SocialUserProperty {

    @JsonProperty("kakao_account")
    private Map<String, String> accountInfo;

    @JsonProperty("id")
    private Long userUniqueId;

    @JsonProperty("properties")
    private Map<String, String> userProperties;

    @Override
    public String getSocialId() {
        return userUniqueId.toString();
    }

    @Override
    public String getUserNickname() {
        return userProperties.get("nickname");
    }

    @Override
    public String getProfileImage() {
        return userProperties.get("profile_image");
    }

    @Override
    public String getThumnailImage() {
        return userProperties.get("thumnail_image");
    }

    @Override
    public String getGender() {
        return accountInfo.get("gender");
    }

    @Override
    public String getBirthDay() {
        return accountInfo.get("birthday");
    }

    @Override
    public String getAgeRange() {
        return accountInfo.get("age_range");
    }

}
