package com.bank.api.controller;

import com.bank.api.service.AccountService;
import com.bank.api.service.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funds")
public class FundTransferController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private FundTransferService fundTransferService;

    @PostMapping("/transfer")
    public String transferFunds(@RequestParam String accountFrom, @RequestParam String accountTo, @RequestParam String amount, @RequestParam String comments) {
        String errorMessage = accountService.verifyAccounts(accountFrom, accountTo);
        if (errorMessage != null) return errorMessage;
        return fundTransferService.transferFunds(accountFrom, accountTo, amount, comments);
    }
}
