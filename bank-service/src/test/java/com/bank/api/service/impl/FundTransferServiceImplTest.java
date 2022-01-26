package com.bank.api.service.impl;

import com.bank.api.domain.Account;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.FundTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.bank.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class FundTransferServiceImplTest {

    @InjectMocks
    FundTransferServiceImpl fundTransferService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    FundTransferRepository fundTransferRepository;

    @Test
    void transferFundsSuccess() {
        Mockito.when(accountRepository.findByAccountNumber(ACCOUNT_1_MOCK.getAccountNumber())).thenReturn(Optional.of(ACCOUNT_1_MOCK));
        Mockito.when(accountRepository.findByAccountNumber(ACCOUNT_2_MOCK.getAccountNumber())).thenReturn(Optional.of(ACCOUNT_2_MOCK));

        String accountFrom = "12345";
        String accountTo = "12344";
        String amount = "500";
        Long amountLongValue = Long.parseLong(amount);

        String actual = fundTransferService.transferFunds(accountFrom, accountTo, amount, TEST_QUICK_TRANSFER);


        Account accountFromDb = accountRepository.findByAccountNumber(Long.parseLong(accountFrom)).get();
        Account accountToDb = accountRepository.findByAccountNumber(Long.parseLong(accountTo)).get();

        assertEquals("Fund transfer successfully !", actual);
        assertEquals(accountFromDb.getOpeningBalance() - amountLongValue, accountFromDb.getCurrentBalance());
        assertEquals(accountToDb.getOpeningBalance() + amountLongValue, accountToDb.getCurrentBalance());

    }

    @Test
    void transferFundsNoAccountFound() {
        Mockito.when(accountRepository.findByAccountNumber(ACCOUNT_2_MOCK.getAccountNumber())).thenReturn(Optional.of(ACCOUNT_2_MOCK));

        String accountFrom = "12345";
        String accountTo = "12344";
        String amount = "500";

        String actual = fundTransferService.transferFunds(accountFrom, accountTo, amount, TEST_QUICK_TRANSFER);

        assertEquals("Please provide correct information!", actual);
    }

    @Test
    void transferFundsInsufficientFunds() {
        Mockito.when(accountRepository.findByAccountNumber(ACCOUNT_1_MOCK.getAccountNumber())).thenReturn(Optional.of(ACCOUNT_1_MOCK));
        Mockito.when(accountRepository.findByAccountNumber(ACCOUNT_2_MOCK.getAccountNumber())).thenReturn(Optional.of(ACCOUNT_2_MOCK));

        String accountFrom = "12345";
        String accountTo = "12344";
        String amount = String.valueOf((ACCOUNT_1_MOCK.getCurrentBalance() + 10L));

        String actual = fundTransferService.transferFunds(accountFrom, accountTo, amount, TEST_QUICK_TRANSFER);

        assertEquals("Insufficient funds!", actual);

    }

}