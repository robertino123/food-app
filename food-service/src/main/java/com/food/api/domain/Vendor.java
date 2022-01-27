package com.food.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vendor implements Serializable {

    @Id
    private Long id;

    private String name;

    private String description;

}
