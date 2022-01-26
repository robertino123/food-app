package com.bank.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FundTransfer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account accountFrom;

    @ManyToOne
    private Account accountTo;

    private Date date;

    private Long amount;

    private String comments;

    @Transient
    @Enumerated(EnumType.STRING)
    private FundTransferType fundTransferType;

    public FundTransfer(Account accountFrom, Account accountTo, Date date, Long amount, String comments) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.date = date;
        this.amount = amount;
        this.comments = comments;
    }
}
