package com.bank.api.service.impl;

import com.bank.api.domain.Account;
import com.bank.api.domain.FundTransfer;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.FundTransferRepository;
import com.bank.api.service.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.bank.api.utils.Constants.*;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Override
    public String transferFunds(String accountFrom, String accountTo, String amount, String comments) {
        Optional<Account> accountFromTransfer = accountRepository.findByAccountNumber(Long.parseLong(accountFrom));
        Optional<Account> accountToTransfer = accountRepository.findByAccountNumber(Long.parseLong(accountTo));

        if (accountFromTransfer.isPresent() && accountToTransfer.isPresent()) {
            Account accountFr = accountFromTransfer.get();
            Account accountToT = accountToTransfer.get();

            if ((accountFr.getCurrentBalance() < Long.parseLong(amount))) {
                return INSUFFICIENT_FUNDS;
            }

            setBalanceOnAccounts(amount, accountFr, accountToT);

            accountRepository.save(accountFr);
            accountRepository.save(accountToT);

            doTransfer(amount, comments.trim(), accountFr, accountToT);

            return SUCCESS_TRANSFER;
        } else {
            return INCORRECT_INFO_ERROR;
        }
    }

    private void setBalanceOnAccounts(String amount, Account accountFr, Account accountToT) {
        long remainingBalanceOfCreditAccount = getRemainingBalanceOfCreditAccount(amount, accountFr);
        long totalBalanceOfDebitedAccount = getTotalBalanceOfDebitedAccount(amount, accountToT);

        accountFr.setCurrentBalance(remainingBalanceOfCreditAccount);
        accountToT.setCurrentBalance(totalBalanceOfDebitedAccount);
    }

    private void doTransfer(String amount, String comments, Account accountFr, Account accountToT) {
        FundTransfer fundTransferSent = new FundTransfer(accountFr, accountToT, new Date(), Long.parseLong(amount), comments);
        fundTransferRepository.save(fundTransferSent);
    }

    private long getTotalBalanceOfDebitedAccount(String amount, Account accountToT) {
        return accountToT.getCurrentBalance() + Long.parseLong(amount);
    }

    private long getRemainingBalanceOfCreditAccount(String amount, Account accountFr) {
        return accountFr.getCurrentBalance() - Long.parseLong(amount);
    }
}
