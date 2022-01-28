package com.food.api.service;

import com.food.api.domain.FoodOrderRequest;
import com.food.api.dto.FoodOrderDTO;

import java.util.List;

public interface FoodOrderService {
    FoodOrderDTO processOrder(FoodOrderRequest foodOrderRequest) throws Exception;

    List<FoodOrderDTO> getHistory(Long userId, Integer pageNumber, Integer pageSize);
}
