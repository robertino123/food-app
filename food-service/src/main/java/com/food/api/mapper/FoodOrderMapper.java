package com.food.api.mapper;

import com.food.api.domain.FoodOrder;
import com.food.api.dto.FoodOrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FoodItemMapper.class})
public interface FoodOrderMapper {
    FoodOrderDTO toDto(FoodOrder foodOrder);

    FoodOrder toEntity(FoodOrderDTO foodOrderDTO);
}
