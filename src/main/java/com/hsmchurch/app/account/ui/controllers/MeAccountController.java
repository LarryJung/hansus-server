package com.hsmchurch.app.account.ui.controllers;

import com.hsmchurch.app.account.application.AccountService;
import com.hsmchurch.app.account.ui.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.hsmchurch.app.security.AuthenticationHelper.currentUserId;

@RestController
@RequestMapping(path = "/api/v1/me/accounts")
@RequiredArgsConstructor
public class MeAccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<AccountResponse> getMeInfo() {
        final AccountResponse accountResponse = accountService.findById(currentUserId()).toResponse();
        return ResponseEntity.ok(accountResponse);
    }

}
