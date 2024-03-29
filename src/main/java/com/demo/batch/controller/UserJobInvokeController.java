package com.demo.batch.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserJobInvokeController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("userJob")
	private Job userProcessingJob;
	
	@RequestMapping("/run-user-process-job")
	 public String handle() throws Exception {
		 
        JobParameters jobParameters = new JobParametersBuilder()
        								.addString("source", "Spring Boot")
        								.toJobParameters();
        jobLauncher.run(userProcessingJob, jobParameters);
        
    return "Batch job has been invoked";
}
}
