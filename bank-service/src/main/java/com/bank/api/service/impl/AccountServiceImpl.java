package com.bank.api.service.impl;

import com.bank.api.domain.Account;
import com.bank.api.domain.AccountType;
import com.bank.api.domain.User;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.UserRepository;
import com.bank.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import static com.bank.api.utils.Constants.ACCOUNT_CREATED;
import static com.bank.api.utils.Constants.ACCOUNT_NOT_FOUND;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String createAccount(User user) {
        userRepository.save(user);
        Long accountNumber = getAccountNumber();
        Account account = new Account(user, accountNumber, new Date(), 50000L, 50000L, AccountType.CURRENT_ACCOUNT);
        accountRepository.save(account);
        return ACCOUNT_CREATED + accountNumber + " account type : " + account.getAccountType();
    }

    @Override
    public String verifyAccounts(String accountFrom, String accountTo) {
        if (!accountRepository.findByAccountNumber(Long.parseLong(accountFrom)).isPresent() || !accountRepository.findByAccountNumber(Long.parseLong(accountTo)).isPresent()) {
            return ACCOUNT_NOT_FOUND;
        }
        return null;
    }

    @Override
    public Long getUserId(String accountNumber) throws Exception {
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(Long.parseLong(accountNumber));
        if (accountOptional.isPresent()) {
            return accountOptional.get().getUser().getId();
        } else {
            throw new Exception(ACCOUNT_NOT_FOUND);
        }
    }

    private Long getAccountNumber() {
        Random rand = new Random();
        String accNoString = String.format("%04d", rand.nextInt(10000));
        return Long.parseLong(accNoString);
    }

}
