package com.food.api.mapper;

import com.food.api.domain.FoodOrder;
import com.food.api.dto.FoodOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FoodItemMapper.class})
public interface FoodOrderMapper {

    @Mapping(target = "createdDate", ignore = true)
    FoodOrderDTO toDto(FoodOrder foodOrder);

    @Mapping(target = "createdDate", ignore = true)
    FoodOrder toEntity(FoodOrderDTO foodOrderDTO);
}
