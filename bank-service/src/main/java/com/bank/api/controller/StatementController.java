package com.bank.api.controller;

import com.bank.api.dto.FundTransferDTO;
import com.bank.api.repository.AccountRepository;
import com.bank.api.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

import static com.bank.api.utils.Constants.INCORRECT_INFO_ERROR;

@RestController
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StatementService statementService;

    @GetMapping
    public List<FundTransferDTO> getStatement(@RequestParam int month, @RequestParam int year, @RequestParam String accountNumber) {
        if (year > Calendar.getInstance().get(Calendar.YEAR) || String.valueOf(year).length() < 4 || month > 12 || month < 0 || !accountRepository.findByAccountNumber(Long.parseLong(accountNumber)).isPresent()) {
            throw new IllegalArgumentException(INCORRECT_INFO_ERROR);
        }

        return statementService.getStatement(month, year, accountNumber);
    }
}
