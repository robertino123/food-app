package com.bank.api.service.impl;

import com.bank.api.mapper.UserMapper;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.bank.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AccountServiceImplTest {


    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Test
    public void createAccountShouldCreate() {
        Mockito.when(userRepository.save(USER_1_MOCK)).thenReturn(USER_1_MOCK);
        Mockito.when(accountRepository.save(ACCOUNT_1_MOCK)).thenReturn(ACCOUNT_1_MOCK);

        String actual = accountService.createAccount(USER_DTO_MOCK);
        assertNotNull(actual);
    }

    @Test
    void verifyAccountsReturnsOK() {
        String accountFrom = "12345";
        String accountTo = "12344";
        Mockito.when(accountRepository.findByAccountNumber(Long.parseLong(accountFrom))).thenReturn(Optional.of(ACCOUNT_1_MOCK));
        Mockito.when(accountRepository.findByAccountNumber(Long.parseLong(accountTo))).thenReturn(Optional.of(ACCOUNT_2_MOCK));

        String s = accountService.verifyAccounts(accountFrom, accountTo);
        assertNull(s);
    }

    @Test
    void verifyAccountsNoAccountFound() {
        String accountFrom = "12345";
        String accountTo = "12344";
        Mockito.when(accountRepository.findByAccountNumber(Long.parseLong(accountFrom))).thenReturn(Optional.of(ACCOUNT_1_MOCK));

        String actual = accountService.verifyAccounts(accountFrom, accountTo);
        String expected = "Please provide existing accounts information !";
        assertEquals(expected, actual);
    }

    @Test
    void getUserIdReturnsOK() throws Exception {
        String accountNumber = "12345";
        Mockito.when(accountRepository.findByAccountNumber(Long.parseLong(accountNumber))).thenReturn(Optional.of(ACCOUNT_1_MOCK));
        Long actual = accountService.getUserId(accountNumber);
        assertEquals(ACCOUNT_1_MOCK.getUser().getId(), actual);
    }

    @Test
    void getUserIdThrowsException() {
        String accountNumber = "12345";
        assertThrows(Exception.class, () -> accountService.getUserId(accountNumber));
    }


}