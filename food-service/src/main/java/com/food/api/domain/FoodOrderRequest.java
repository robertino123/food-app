package com.food.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodOrderRequest {
    private String accountNumber;
    private List<FoodQuantityRequest> foodQty;
}
