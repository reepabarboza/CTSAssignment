package com.abc.controller;

import java.math.BigDecimal;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;

@Controller
public class PolicyTransactionController {
	
	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;
    
    @Autowired
    PolicyDataProcessor policyDataProcessor;
    
	@GetMapping("/")
    public String uploadPolicy(Model model) {
        return "policyUpload";
    }
	
	@PostMapping("/submit")
    public String launchPolicyUploadJob(@RequestParam("inputFilePath") String inputFilePath,
    							   @RequestParam("outputFilePath") String outputFilePath,
                                   RedirectAttributes redirectAttributes) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
        JobParameters jobParameters =
      		  new JobParametersBuilder().addLong("runId", System.currentTimeMillis())
      		  .addString("inputFilePath",inputFilePath).addString("outputFilePath",outputFilePath).toJobParameters();
        
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        
        redirectAttributes.addFlashAttribute("status", jobExecution.getStatus());
        redirectAttributes.addFlashAttribute("startTime", jobExecution.getStartTime());
        redirectAttributes.addFlashAttribute("endTime", jobExecution.getEndTime());
        
        return "redirect:/";
    }
	
	@RequestMapping("/processPolicy")
    public @ResponseBody PolicyTransaction processPolicy(@RequestParam Long policyId, 
    		@RequestParam Long policyHolderId, @RequestParam String dateOfService,
    		@RequestParam String coverageMainCategory,
    		@RequestParam String coverageSubCategory,
    		@RequestParam BigDecimal billedAmount) throws Exception {
		
		PolicyTransaction policyTransaction = new PolicyTransaction();
		policyTransaction.setPolicyId(policyId);
		policyTransaction.setPolicyHolderId(policyHolderId);
		policyTransaction.setDateOfService(dateOfService);
		policyTransaction.setCoverageMainCategory(coverageMainCategory);
		policyTransaction.setCoverageSubCategory(coverageSubCategory);
		policyTransaction.setBilledAmount(billedAmount);
		return policyDataProcessor.process(policyTransaction);
    }
	
	
} 
