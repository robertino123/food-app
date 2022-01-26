package com.bank.api.service.impl;

import com.bank.api.domain.FundTransfer;
import com.bank.api.domain.FundTransferType;
import com.bank.api.dto.FundTransferDTO;
import com.bank.api.repository.AccountRepository;
import com.bank.api.repository.FundTransferRepository;
import com.bank.api.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.bank.api.utils.Constants.getFirstDayOfMonth;

@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Override
    public List<FundTransferDTO> getStatement(int month, int year, String accountNumber) {
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

        return getTransfers(fundTransferRepository.findAllByAccountNumberAndDate(date1, date2, Long.parseLong(accountNumber)), Long.parseLong(accountNumber));
    }

    private List<FundTransferDTO> getTransfers(List<FundTransfer> allTransfers, long accountNumber) {
        return allTransfers
                .stream()
                .peek(ft -> {
                    if (ft.getAccountFrom().getAccountNumber().equals(accountNumber)) {
                        ft.setFundTransferType(FundTransferType.CREDIT);
                    } else {
                        ft.setFundTransferType(FundTransferType.DEBIT);
                    }
                })
                .map(FundTransferDTO::new)
                .collect(Collectors.toList());
    }
}
