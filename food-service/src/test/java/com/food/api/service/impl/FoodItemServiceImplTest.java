package com.food.api.service.impl;

import com.food.api.domain.FoodItem;
import com.food.api.dto.FoodItemDTO;
import com.food.api.mapper.FoodItemMapper;
import com.food.api.repository.FoodItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FoodItemServiceImplTest {

    @InjectMocks
    private FoodItemServiceImpl foodItemService;

    @Mock
    private FoodItemRepository foodItemRepository;

    @Mock
    private FoodItemMapper foodItemMapper;

    @Test
    void searchFoods() {
        FoodItem foodItem = new FoodItem();
        String searchTerm = "test";
        Mockito.when(foodItemRepository.searchByTerm("%test%")).thenReturn(List.of(foodItem));
        List<FoodItemDTO> actual = foodItemService.searchFoods(searchTerm);

        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    void searchFoodsNoItem() {
        List<FoodItemDTO> actual = foodItemService.searchFoods(null);
        assertEquals(0, actual.size());
    }
}