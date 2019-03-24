package com.hsmchurch.app.security.oauth;

import com.hsmchurch.app.account.domain.Account;
import com.hsmchurch.app.account.domain.AccountRepository;
import com.hsmchurch.app.account.domain.AccountOrigin;
import com.hsmchurch.app.account.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private AccountRepository accountRepository;

    @Resource(name = "SocialFetchService")
    private SocialFetchService SocialFetchService;

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
        SocialUserProperty property = SocialFetchService.getSocialUserInfo(dto);
        log.info("properties : {}", property.getUserNickname());
        String socialId = property.getSocialId();
        SocialProviders provider = dto.getProvider();
        log.info("userid, provider : {}, {}", socialId, provider.getUserinfoEndpoint());
        return accountRepository.findBySocialIdAndAccountOrigin(socialId, AccountOrigin.valueOf(provider.getProviderName()))
                .orElseGet(() -> accountRepository.save(
                        Account.builder()
                                .name(property.getUserNickname())
                                .password(String.valueOf(UUID.randomUUID().getMostSignificantBits()))
                                .role(Role.MEMBER)
                                .socialId(socialId)
                                .accountOrigin(AccountOrigin.valueOf(provider.getProviderName()))
                                .gender(property.getGender())
                                .ageRange(property.getAgeRange())
                                .birthday(property.getBirthDay())
                                .build()));
    }

}