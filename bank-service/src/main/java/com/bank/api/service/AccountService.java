package com.bank.api.service;

import com.bank.api.domain.User;

public interface AccountService {
    String createAccount(User user);

    String verifyAccounts(String accountFrom, String accountTo);

    Long getUserId(String accountNumber) throws Exception;
}
