package com.hsmchurch.app.account.service;

import com.hsmchurch.app.account.entity.Account;
import com.hsmchurch.app.account.entity.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public Account findById(final Long userId) {
        return accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
