package com.food.api.service.impl;

import com.food.api.domain.FoodItem;
import com.food.api.domain.FoodOrder;
import com.food.api.domain.Vendor;
import com.food.api.dto.FoodItemDTO;
import com.food.api.dto.FoodOrderDTO;
import com.food.api.dto.VendorDTO;
import com.food.api.mapper.FoodOrderMapper;
import com.food.api.repository.FoodOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class FoodOrderServiceImplTest {

    @InjectMocks
    private FoodOrderServiceImpl foodOrderService;

    @Mock
    private FoodOrderRepository foodOrderRepository;

    @Mock
    private FoodOrderMapper foodOrderMapper = Mappers.getMapper(FoodOrderMapper.class);

    @Test
    void getHistory() {
        FoodItem foodItem = new FoodItem(1L, "test", "test", new Vendor(), 10.0f);
        FoodOrder entity = new FoodOrder(List.of(foodItem), 20.0f, 0L, new Date());

        Mockito.when(foodOrderRepository.findAllByUserId(0L)).thenReturn(List.of(entity));

        FoodItemDTO foodItemDTO = new FoodItemDTO(foodItem.getId(), foodItem.getName(), foodItem.getDescription(), new VendorDTO(), foodItem.getPrice());
        Mockito.when(foodOrderMapper.toDto(entity)).thenReturn(new FoodOrderDTO(1L, List.of(foodItemDTO), 12.0f, 1L, new Date().toString()));

        List<FoodOrderDTO> actual = foodOrderService.getHistory(0L, null, null);

        assertNotNull(actual);
        assertEquals(1, actual.size());
    }
}