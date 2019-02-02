package com.hsmchurch.app.account.service;

import com.hsmchurch.app.account.Account;
import com.hsmchurch.app.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public Account findById(Long userId) {
        return accountRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }
}
