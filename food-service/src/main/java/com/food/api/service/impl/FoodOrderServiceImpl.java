package com.food.api.service.impl;

import com.food.api.domain.FoodItem;
import com.food.api.domain.FoodOrder;
import com.food.api.domain.FoodOrderRequest;
import com.food.api.domain.FoodQuantityRequest;
import com.food.api.dto.FoodOrderDTO;
import com.food.api.feignclient.AccountClient;
import com.food.api.feignclient.FundTransferClient;
import com.food.api.mapper.FoodOrderMapper;
import com.food.api.repository.FoodItemRepository;
import com.food.api.repository.FoodOrderRepository;
import com.food.api.service.FoodOrderService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FoodOrderServiceImpl implements FoodOrderService {

    public static final String FUND_TRANSFER_SUCCESSFULLY = "Fund transfer successfully !";

    @Value("${food-app.account.number}")
    private String accountNumber;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private FundTransferClient fundTransferClient;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodOrderMapper foodOrderMapper;

    @Override
    public FoodOrderDTO processOrder(FoodOrderRequest foodOrderRequest) throws Exception {

        Long userId;
        try {
            userId = accountClient.getUserId(foodOrderRequest.getAccountNumber());
        } catch (FeignException.InternalServerError ex) {
            throw new Exception("Account not found, please provide an existing account number !");
        }

        List<FoodItem> foodItems = new ArrayList<>();
        List<FoodQuantityRequest> foodList = foodOrderRequest.getFoodQty();
        AtomicReference<Float> totalAmount = new AtomicReference<>(0f);

        foodList.forEach(food -> foodItemRepository.findById(food.getFoodId()).ifPresent(foodItem -> {
            foodItems.add(foodItem);
            totalAmount.updateAndGet(v -> v + (foodItem.getPrice() * food.getQuantity()));
        }));

        String response = fundTransferClient.transferFunds(foodOrderRequest.getAccountNumber(), accountNumber, totalAmount.get().toString(), "Payment for food order !");

        if (!response.equals(FUND_TRANSFER_SUCCESSFULLY)) {
            throw new Exception(response);
        } else {
            FoodOrder foodOrder = new FoodOrder(foodItems, totalAmount.get(), userId, new Date());
            FoodOrder savedOrder = foodOrderRepository.save(foodOrder);

            return getOrderDTO(savedOrder);
        }
    }

    @Override
    public List<FoodOrderDTO> getHistory(Long userId, Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageSize == null) {
            return foodOrderRepository.findAllByUserId(userId)
                    .stream()
                    .map(this::getOrderDTO)
                    .collect(Collectors.toList());
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            return foodOrderRepository.findAllByUserId(userId, pageable)
                    .stream()
                    .map(this::getOrderDTO)
                    .collect(Collectors.toList());
        }
    }

    private FoodOrderDTO getOrderDTO(FoodOrder foodOrder) {
        FoodOrderDTO foodOrderDTO = foodOrderMapper.toDto(foodOrder);
        String dateFormatted = getDateFormatted(foodOrder.getCreatedDate());
        foodOrderDTO.setCreatedDate(dateFormatted);
        return foodOrderDTO;
    }

    private String getDateFormatted(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return format.format(date);
    }
}
