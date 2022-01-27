package com.food.api.service;

import com.food.api.dto.FoodItemDTO;

import java.util.List;

public interface FoodItemService {
    List<FoodItemDTO> searchFoods(String searchTerm);
}
