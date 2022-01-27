package com.food.api.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "http://bank-service/bank/account")
public interface AccountClient {
    @GetMapping("/getByAccountNo")
    Long getUserId(@RequestParam("accountNumber") String accountNumber);
}
