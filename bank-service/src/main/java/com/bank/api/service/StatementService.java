package com.bank.api.service;

import com.bank.api.dto.FundTransferDTO;

import java.util.List;

public interface StatementService {
    List<FundTransferDTO> getStatement(int month, int year, String accountNumber);
}
