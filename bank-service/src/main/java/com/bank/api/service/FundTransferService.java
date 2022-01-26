package com.bank.api.service;

public interface FundTransferService {
    String transferFunds(String accountFrom, String accountTo, String amount, String comments);
}
