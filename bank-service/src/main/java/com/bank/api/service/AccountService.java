package com.bank.api.service;

import com.bank.api.dto.UserDTO;

public interface AccountService {
    String createAccount(UserDTO user);

    String verifyAccounts(String accountFrom, String accountTo);

    Long getUserId(String accountNumber) throws Exception;
}
