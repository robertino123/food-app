package com.food.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodOrderDTO {

    @JsonIgnore
    private Long id;

    private List<FoodItemDTO> foodItems;

    private Float totalPrice;

    private Long userId;

    private Date createdDate;
}
