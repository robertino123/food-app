package com.bank.api.dto;

import com.bank.api.domain.FundTransfer;
import com.bank.api.domain.FundTransferType;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class FundTransferDTO {
    private Long id;
    private Long accountFrom;
    private Long accountTo;
    private String date;
    private Long amount;
    private String comments;
    private FundTransferType transferType;

    public FundTransferDTO(FundTransfer fundTransfer) {
        this.id = fundTransfer.getId();
        this.accountFrom = fundTransfer.getAccountFrom().getAccountNumber();
        this.accountTo = fundTransfer.getAccountTo().getAccountNumber();
        setDate(fundTransfer.getDate());
        this.amount = fundTransfer.getAmount();
        this.comments = fundTransfer.getComments();
        this.transferType = fundTransfer.getFundTransferType();
    }

    private void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.format(date);
    }
}
