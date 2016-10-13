package com.abc.controller;

import java.math.BigDecimal;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.batch.AsyncJobLauncher;
import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;
import com.abc.repository.BatchJobExecutionRepository;

@Controller
public class PolicyTransactionController {

	@Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    PolicyDataProcessor policyDataProcessor;
    
    @Autowired
    AsyncJobLauncher asyncJobLauncher;
    
    @Autowired
    BatchJobExecutionRepository batchJobExecutionRepository;
    
    
	/**
	 * Specifies the view name policyUpload
	 * @param model
	 * @return
	 */
	@GetMapping("/")
    public String uploadPolicy(Model model) {
		
		model.addAttribute("jobExecutionDetails", batchJobExecutionRepository.findAll());
        return "view/policyUpload";
    }
	
	
    /**
     * Launch asynchronous job with input and output file paths
     * @param inputFilePath
     * @param outputFilePath
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submit" , method = RequestMethod.POST)
    public String launchPolicyUploadJob(@RequestParam("inputFilePath") String inputFilePath,
    							   @RequestParam("outputFilePath") String outputFilePath) throws Exception {

		asyncJobLauncher.launchJob(inputFilePath, outputFilePath);
		
		return "redirect:/";
	}
	
    
	/**
	 * Based on the below @param values, process the policy 
	 * and return it with information such as amount paid by policy holder, amount paid by plan, error info
	 * @param policyId
	 * @param policyHolderId
	 * @param dateOfService
	 * @param coverageMainCategory
	 * @param coverageSubCategory
	 * @param billedAmount
	 * @return
	 * @throws Exception
	 */
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
