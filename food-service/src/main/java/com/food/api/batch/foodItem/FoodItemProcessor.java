package com.food.api.batch.foodItem;

import com.food.api.domain.FoodItem;
import com.food.api.domain.FoodItemHelper;
import com.food.api.domain.Vendor;
import com.food.api.repository.FoodItemRepository;
import com.food.api.repository.VendorRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FoodItemProcessor implements ItemProcessor<FoodItemHelper, FoodItemHelper> {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public FoodItemHelper process(FoodItemHelper foodItem) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(foodItem.getId());
        foodItemOptional.ifPresent(fi -> {
            fi.setDescription(foodItem.getDescription());
            fi.setName(foodItem.getName());
            fi.setPrice(foodItem.getPrice());
            Optional<Vendor> vendorOptional = vendorRepository.findById(foodItem.getVendorId());
            vendorOptional.ifPresent(fi::setVendor);
        });
        return foodItem;
    }
}
