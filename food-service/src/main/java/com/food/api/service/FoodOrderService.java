package com.food.api.service;

import com.food.api.domain.FoodOrderRequest;
import com.food.api.dto.FoodOrderDTO;
import org.springframework.data.domain.Page;

public interface FoodOrderService {
    FoodOrderDTO processOrder(FoodOrderRequest foodOrderRequest) throws Exception;

    Page<FoodOrderDTO> getHistory(Long userId, int pageNumber, int pageSize);
}
