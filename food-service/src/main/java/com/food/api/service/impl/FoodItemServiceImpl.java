package com.food.api.service.impl;

import com.food.api.dto.FoodItemDTO;
import com.food.api.mapper.FoodItemMapper;
import com.food.api.repository.FoodItemRepository;
import com.food.api.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodItemMapper foodItemMapper;

    @Override
    public List<FoodItemDTO> searchFoods(String searchTerm) {
        String query = '%' + searchTerm + '%';
        return foodItemRepository.searchByTerm(query)
                .stream()
                .map(foodItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
