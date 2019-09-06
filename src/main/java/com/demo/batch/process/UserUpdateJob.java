package com.demo.batch.process;

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

import com.demo.batch.entity.User;

@Component
public class UserUpdateJob extends JobExecutionListenerSupport {

	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}")
	Resource resource;
	
	@Autowired
	CustomProcessor customProcessor;
	
	@Autowired
	CustomWriter customWriter;
	
	@Bean(name = "userJob")
	public Job userProcessingJob() {
		
		Step step = stepBuilderFactory.get("step-1")
				.<User , User> chunk(2)
				.reader(new CustomReader(resource))
				.processor(customProcessor)
				.writer(customWriter)
				.build();
		
		Job job = jobBuilderFactory.get("user-processor-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();
		
		return job;
				
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}
}
