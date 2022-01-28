package com.food.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodQuantityRequest {
    private Long foodId;
    private Integer quantity;
}
