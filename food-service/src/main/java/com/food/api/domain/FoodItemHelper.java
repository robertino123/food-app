package com.food.api.domain;

import lombok.Data;

@Data
public class FoodItemHelper {
    private Long id;
    private String name;
    private String description;
    private Long vendorId;
    private Float price;
}
