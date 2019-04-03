package com.hsmchurch.app.account.ui.controllers;

import com.hsmchurch.app.account.application.AccountService;
import com.hsmchurch.app.account.domain.Account;
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

    @GetMapping("/{userId}")
    public ResponseEntity<AccountResponse> getOneById(@PathVariable final Long userId) {
        final AccountResponse accountResponse = accountService.findById(userId).toResponse();
        return ResponseEntity.ok(accountResponse);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        final List<AccountResponse> accountResponses = accountService.findAll().stream()
                .map(Account::toResponse).collect(toList());
        return ResponseEntity.ok(accountResponses);
    }

//    me api 추가

}

