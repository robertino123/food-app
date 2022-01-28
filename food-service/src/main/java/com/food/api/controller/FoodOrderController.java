package com.food.api.controller;

import com.food.api.domain.FoodOrderRequest;
import com.food.api.dto.FoodOrderDTO;
import com.food.api.service.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @PostMapping("/process")
    public ResponseEntity<FoodOrderDTO> processOrder(@RequestBody FoodOrderRequest foodOrderRequest) throws Exception {
        return ResponseEntity.ok(foodOrderService.processOrder(foodOrderRequest));
    }

    @GetMapping("/history")
    public ResponseEntity<List<FoodOrderDTO>> getHistory(@RequestParam Long userId, @RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return ResponseEntity.ok(foodOrderService.getHistory(userId, pageNumber, pageSize));
    }

}
