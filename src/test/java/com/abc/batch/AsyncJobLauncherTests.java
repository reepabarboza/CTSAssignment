package com.abc.batch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * Test class for com.abc.batch.AsyncJobLauncher
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncJobLauncherTests {
	
	@Autowired
	AsyncJobLauncher asyncJobLauncher;
	
	/**
	 * Test launchJob metod by passing valid input and output file path
	 * @throws Exception
	 */
	@Test
    @Transactional
	@Rollback(true)
    public void launchJobTest() throws Exception {
    	Path path = Paths.get("src/main/resources/");
    	String inputFilePath = path + "/SampleInputTransactionFile.csv";
    	String outputFilePath = path + "/SampleOutputTransactionFile.csv";
    	
    	CompletableFuture<JobExecution> jobExecution = asyncJobLauncher.launchJob(inputFilePath, outputFilePath);
        Assert.assertTrue(!jobExecution.isDone());
    }
	
	@Test
    @Transactional
	@Rollback(true)
    public void launchJobInvalidTest() throws Exception {
    	Path path = Paths.get("src/main/resources/");
    	String inputFilePath = path + "/SampleInputTransactionFileInvalid.csv";
    	String outputFilePath = path + "/SampleOutputTransactionFile.csv";
    	
    	CompletableFuture<JobExecution> jobExecution = asyncJobLauncher.launchJob(inputFilePath, outputFilePath);
        Assert.assertTrue(!jobExecution.isDone());
    }
}
