package com.food.api.domain;

import lombok.Data;

@Data
public class FoodQuantityRequest {
    private Long foodId;
    private Integer quantity;
}
