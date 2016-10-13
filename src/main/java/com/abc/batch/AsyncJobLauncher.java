package com.abc.batch;

import java.util.concurrent.CompletableFuture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class AsyncJobLauncher {
	
	@Autowired
    Job job;
	
	@Autowired
    JobLauncher jobLauncher;
	
	
	/**
	 * Runs the job asynchronously with input and output file path as job parameter
	 * @param inputFilePath
	 * @param outputFilePath
	 * @return
	 * @throws JobExecutionAlreadyRunningException
	 * @throws JobRestartException
	 * @throws JobInstanceAlreadyCompleteException
	 * @throws JobParametersInvalidException
	 */
	@Async
	public CompletableFuture<JobExecution> launchJob(String inputFilePath, String outputFilePath) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		JobParameters jobParameters =
      		  new JobParametersBuilder().addLong("runId", System.currentTimeMillis())
      		  .addString("inputFilePath",inputFilePath).addString("outputFilePath",outputFilePath).toJobParameters();
		
		return CompletableFuture.completedFuture(jobLauncher.run(job, jobParameters));
	}
}
