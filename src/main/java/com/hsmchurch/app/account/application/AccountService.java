package com.hsmchurch.app.account.application;

import com.hsmchurch.app.account.ui.response.AccountResponse;
import com.hsmchurch.app.account.domain.Account;
import com.hsmchurch.app.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.hsmchurch.app.common.support.CrudStringFormat.READ_FAIL;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final static String ENTITY_NAME = "계정";
    private final AccountRepository accountRepository;

    public AccountResponse findById(final Long accountId) {
        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.error(READ_FAIL.apply(ENTITY_NAME), accountId, "유저를 찾을 수 없습니다.");
                    return new RuntimeException("유저를 찾을 수 없습니다.");
                });
        return account.toResponse();
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
