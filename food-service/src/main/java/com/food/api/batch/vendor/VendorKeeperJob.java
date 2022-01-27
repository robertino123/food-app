package com.food.api.batch.vendor;

import com.food.api.domain.Vendor;
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
public class VendorKeeperJob extends JobExecutionListenerSupport {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Value("${vendor.file.path}")
    Resource resource;

    @Autowired
    VendorProcessor vendorProcessor;

    @Autowired
    VendorWriter vendorWriter;

    @Bean(name = "vendorJob")
    public Job vendorKeeperJob() {

        Step step = stepBuilderFactory.get("step-1")
                .<Vendor, Vendor>chunk(2)
                .reader(new VendorReader(resource))
                .processor(vendorProcessor)
                .writer(vendorWriter)
                .build();

        return jobBuilderFactory.get("vendor-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("----------BATCH JOB COMPLETED SUCCESSFULLY FOR VENDOR DATA----------");
        }
    }

}
