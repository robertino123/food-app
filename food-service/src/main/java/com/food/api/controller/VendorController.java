package com.food.api.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("vendorJob")
    Job vendorKeeperJob;

    @GetMapping("/import-vendors")
    public String runVendorsJob() throws Exception {
        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addDate("date", new Date());
        jobLauncher.run(vendorKeeperJob, builder.toJobParameters());

        return "Batch job for vendors has been started !";
    }
}
