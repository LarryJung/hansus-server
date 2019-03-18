package com.hsmchurch.app.security.oauth;

import com.hsmchurch.app.security.account.entity.Account;
import com.hsmchurch.app.security.account.entity.AccountRepository;
import com.hsmchurch.app.security.account.entity.value.AccountOrigin;
import com.hsmchurch.app.security.account.entity.value.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository accountRepository;

    @Resource(name = "socialFetchService")
    private SocialFetchService socialFetchService;

    @Override
    public Authentication authenticate(Authentication socialPreAuthentication) throws AuthenticationException {
        Account account = getAccount(((SocialPreAuthorizationToken) socialPreAuthentication).getDto());
        return ((SocialPreAuthorizationToken) socialPreAuthentication).toPostToken(account);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private Account getAccount(SocialLoginDto dto) {
        SocialUserProperty property = socialFetchService.getSocialUserInfo(dto);
        log.info("properties : {}", property.getUserNickname());
        String socialId = property.getSocialId();
        SocialProviders provider = dto.getProvider();
        log.info("userid / provider : {}, {}", socialId, provider.getUserinfoEndpoint());
        return accountRepository.findBySocialIdAndAccountOrigin(socialId, AccountOrigin.valueOf(provider.getProviderName()))
                .orElseGet(() -> accountRepository.save(
                        Account.builder()
                                .name(property.getUserNickname())
                                .password(String.valueOf(UUID.randomUUID().getMostSignificantBits()))
                                .name("SOCIAL_USER")
                                .role(Role.MEMBER)
                                .socialId(socialId)
                                .accountOrigin(AccountOrigin.valueOf(provider.getProviderName()))
                                .build()));
    }

}
