package com.bank.api.repository;

import com.bank.api.domain.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {

    @Query(value = "SELECT ft " +
            "FROM FundTransfer ft " +
            "WHERE ft.accountFrom.accountNumber = :accountNumber OR ft.accountTo.accountNumber = :accountNumber " +
            "AND ft.date BETWEEN :date1 AND :date2 ")
    List<FundTransfer> findAllByAccountNumberAndDate(@Param("date1") Date date1, @Param("date2") Date date2, @Param("accountNumber") Long accountNumber);
}
