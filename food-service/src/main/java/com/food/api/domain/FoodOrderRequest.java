package com.food.api.domain;

import lombok.Data;

import java.util.List;

@Data
public class FoodOrderRequest {
    private String accountNumber;
    private List<FoodQuantityRequest> foodQty;
}
