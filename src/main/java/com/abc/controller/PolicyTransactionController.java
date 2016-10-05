package com.abc.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abc.constant.Constants;

@Controller
public class PolicyTransactionController {
	
	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
	@GetMapping("/")
    public String listUploadedFiles(Model model) {
        return "policyUpload";
    }
	
	@PostMapping("/")
    public String handleFileUpload(@RequestParam("inputFilePath") String inputFilePath,
    							   @RequestParam("outputFilePath") String outputFilePath,
                                   RedirectAttributes redirectAttributes) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {

		
        redirectAttributes.addFlashAttribute("message",
        		Constants.PROCESSING_SUCCESS_MSG);

        JobParameters jobParameters =
      		  new JobParametersBuilder().addLong("runId", System.currentTimeMillis())
      		  .addString("inputFilePath",inputFilePath).addString("outputFilePath",outputFilePath).toJobParameters();
        
        jobLauncher.run(job, jobParameters);
        return "redirect:/";
    }
	
	
}
