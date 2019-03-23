package com.hsmchurch.app.account.ui.controllers;

import com.hsmchurch.app.account.application.AccountService;
import com.hsmchurch.app.account.ui.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getOneById(@PathVariable final Long id) {
        final AccountResponse accountResponse = accountService.findById(id);
        return ResponseEntity.ok(accountResponse);
    }

}

