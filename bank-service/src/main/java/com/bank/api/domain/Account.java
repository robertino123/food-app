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
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Long accountNumber;

    private Date openingDate;

    private Long openingBalance;

    private Long currentBalance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    public Account(User user, Long accountNumber, Date openingDate, Long openingBalance, Long currentBalance, AccountType accountType) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.openingDate = openingDate;
        this.openingBalance = openingBalance;
        this.currentBalance = currentBalance;
        this.accountType = accountType;
    }
}
