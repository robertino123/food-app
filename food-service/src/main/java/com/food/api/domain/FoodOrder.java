package com.food.api.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<FoodItem> foodItems;

    private Float totalPrice;

    private Long userId;

    private Date createdDate;

    public FoodOrder(List<FoodItem> foodItems, Float totalPrice, Long userId, Date createdDate) {
        this.foodItems = foodItems;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.createdDate = createdDate;
    }
}
