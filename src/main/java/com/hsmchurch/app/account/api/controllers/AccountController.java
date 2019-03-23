package com.hsmchurch.app.account.api.controllers;

import com.hsmchurch.app.account.api.dto.response.AccountResponse;
import com.hsmchurch.app.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getOneById(@PathVariable final Long id) {
        final AccountResponse accountResponse = accountService.findById(id);
        return ResponseEntity.ok(accountResponse);
    }

}
