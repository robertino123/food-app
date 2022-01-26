package com.bank.utils;

import com.bank.api.domain.Account;
import com.bank.api.domain.AccountType;
import com.bank.api.domain.User;
import com.bank.api.dto.UserDTO;

import java.util.Date;

public final class Constants {
    public static final User USER_1_MOCK = new User(1L, "fNameTest1", "lNameTest1", 19, 9876212124L, "test1@email.com");
    public static final User USER_2_MOCK = new User(2L, "fNameTest2", "lNameTest2", 21, 9876341224L, "test2@email.com");
    public static final Account ACCOUNT_1_MOCK = new Account(USER_1_MOCK, 12345L, new Date(), 500000L, 500000L, AccountType.CURRENT_ACCOUNT);
    public static final Account ACCOUNT_2_MOCK = new Account(USER_2_MOCK, 12344L, new Date(), 100000L, 100000L, AccountType.CURRENT_ACCOUNT);
    public static final UserDTO USER_DTO_MOCK = new UserDTO(1L, "fNameTest1", "lNameTest1", 19, 9876212124L, "test1@email.com");
    public static final String TEST_QUICK_TRANSFER = "test quick transfer";

}
