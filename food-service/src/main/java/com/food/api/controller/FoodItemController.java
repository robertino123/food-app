package com.food.api.controller;

import com.food.api.dto.FoodItemDTO;
import com.food.api.service.FoodItemService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/item")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("foodItemJob")
    Job foodItemKeeperJob;

    @GetMapping("/import-foods")
    public String runFoodsJob() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        jobLauncher.run(foodItemKeeperJob, builder.toJobParameters());

        return "Batch job for food items has been started !";
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodItemDTO>> searchFoods(@RequestParam String searchTerm) {
        return ResponseEntity.ok(foodItemService.searchFoods(searchTerm));
    }
}