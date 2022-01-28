package com.bank.api.service.impl;

import com.bank.api.domain.FundTransfer;
import com.bank.api.domain.FundTransferType;
import com.bank.api.dto.FundTransferDTO;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.FundTransferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bank.api.utils.Constants.getFirstDayOfMonth;
import static com.bank.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class StatementServiceImplTest {

    @InjectMocks
    StatementServiceImpl statementService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    FundTransferRepository fundTransferRepository;

    @Test
    void getStatementReturnsOk() {
        int month = 12;
        int year = 2022;
        Long accountNumber = ACCOUNT_1_MOCK.getAccountNumber();


        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(getFirstDayOfMonth(month, year));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date date2 = c.getTime();

        FundTransfer fundTransfer = new FundTransfer(ACCOUNT_1_MOCK, ACCOUNT_2_MOCK, date1, 500f, TEST_QUICK_TRANSFER);
        Mockito.when(fundTransferRepository.findAllByAccountNumberAndDate(date1, date2, accountNumber)).thenReturn(Arrays.asList(fundTransfer));

        List<FundTransferDTO> actual = statementService.getStatement(month, year, accountNumber.toString());

        assertEquals(1, actual.size());
        assertEquals(FundTransferType.CREDIT, actual.get(0).getTransferType());
    }

    @Test
    void getStatementInvalidAccountNumber() {
        int month = 12;
        int year = 2022;
        String accountNumber = "13451324";

        List<FundTransferDTO> actual = statementService.getStatement(month, year, accountNumber);

        assertEquals(0, actual.size());
    }
}