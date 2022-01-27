package com.food.api.batch.foodItem;

import com.food.api.domain.FoodItemHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FoodItemKeeperJob extends JobExecutionListenerSupport {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Value("${food-item.file.path}")
    Resource resource;

    @Autowired
    FoodItemProcessor foodItemProcessor;

    @Autowired
    FoodItemWriter foodItemWriter;

    @Bean(name = "foodItemJob")
    public Job foodItemKeeperJob() {
        Step step = stepBuilderFactory.get("step-1")
                .<FoodItemHelper, FoodItemHelper>chunk(2)
                .reader(new FoodItemReader(resource))
                .processor(foodItemProcessor)
                .writer(foodItemWriter)
                .build();

        return jobBuilderFactory.get("foodItem-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("----------BATCH JOB COMPLETED SUCCESSFULLY FOR FOOD ITEMS DATA----------");
        }
    }
}
