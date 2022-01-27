package com.food.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendorDTO {

    @JsonIgnore
    private Long id;

    private String name;

    private String description;
}
