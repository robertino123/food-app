package com.food.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodItem implements Serializable {

    @Id
    private Long id;

    private String name;

    private String description;

    @OneToOne
    private Vendor vendor;

    private Float price;

    public FoodItem(Long id, String name, String description, Float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
