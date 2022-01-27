package com.food.api.mapper;

import com.food.api.domain.FoodItem;
import com.food.api.dto.FoodItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {VendorMapper.class})
public interface FoodItemMapper {
    FoodItemDTO toDto(FoodItem foodItem);

    FoodItem toEntity(FoodItemDTO foodItemDTO);
}
