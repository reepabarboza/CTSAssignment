package com.abc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbcInsuranceProjectApplicationTests {

    private static JobLauncherTestUtils jobLauncherTestUtils;
	
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
	@BeforeJob
	public static void before() {
		jobLauncherTestUtils = new JobLauncherTestUtils();
	}

    @Test
    public void launchJob() throws Exception {

    	JobParameters jobParameters = new JobParametersBuilder()
        		  .addString("inputFilePath","/home/ubantu/PolicyFolder/SampleInputTransactionFile.csv")
        		  .addString("outputFilePath","/home/ubantu/PolicyFolder").toJobParameters();
        
	//testing a job
        //JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

          
    	JobExecution jobExecution = jobLauncher.run(job, jobParameters);
	//Testing a individual step
        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step");

        Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

    }

}
