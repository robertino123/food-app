package com.food.api.batch.foodItem;

import com.food.api.domain.FoodItem;
import com.food.api.domain.FoodItemHelper;
import com.food.api.domain.Vendor;
import com.food.api.repository.FoodItemRepository;
import com.food.api.repository.VendorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FoodItemWriter implements ItemWriter<FoodItemHelper> {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    @Transactional
    public void write(List<? extends FoodItemHelper> foodItems) {
        List<FoodItem> foodsToSave = foodItems.stream().map(this::mapHelperToEntity).collect(Collectors.toList());
        foodItemRepository.saveAll(foodsToSave);
    }

    private FoodItem mapHelperToEntity(FoodItemHelper foodItemHelper) {
        FoodItem foodItem = new FoodItem(foodItemHelper.getId(), foodItemHelper.getName(), foodItemHelper.getDescription(), foodItemHelper.getPrice());
        Optional<Vendor> vendorOptional = vendorRepository.findById(foodItemHelper.getVendorId());
        vendorOptional.ifPresent(foodItem::setVendor);
        return foodItem;
    }
}
