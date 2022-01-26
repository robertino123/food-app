package com.bank.api.controller;

import com.bank.api.domain.User;
import com.bank.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public String createAccount(@Valid @RequestBody User user) {
        return accountService.createAccount(user);
    }

    @GetMapping("/getByAccountNo")
    public Long getUserId(@RequestParam String accountNumber) throws Exception {
        return accountService.getUserId(accountNumber);
    }

}