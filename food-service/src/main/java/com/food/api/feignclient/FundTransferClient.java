package com.food.api.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "http://bank-service/bank/funds")
public interface FundTransferClient {

    @PostMapping("/transfer")
    String transferFunds(@RequestParam("accountFrom") String accountFrom,
                         @RequestParam("accountTo") String accountTo,
                         @RequestParam("amount") String amount,
                         @RequestParam("comments") String comments);
}
