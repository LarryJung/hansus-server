package com.hsmchurch.app.account.ui.controllers;

import com.hsmchurch.app.account.application.AccountService;
import com.hsmchurch.app.account.domain.Account;
import com.hsmchurch.app.account.domain.AccountOrigin;
import com.hsmchurch.app.account.domain.Role;
import com.hsmchurch.app.account.ui.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping(path = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getOneById(@PathVariable final Long id) {
        final AccountResponse accountResponse = accountService.findById(id).toResponse();
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        final List<AccountResponse> accountResponses = accountService.findAll().stream()
                .map(Account::toResponse).collect(toList());
        return ResponseEntity.ok(accountResponses);
    }

    @PostMapping("/hhh")
    public void created() {
        accountService.created(Account.builder()
                .name("ddd")
                .accountOrigin(AccountOrigin.KAKAO)
                .password("password")
                .role(Role.ADMIN)
                .build()
        );
    }
}

