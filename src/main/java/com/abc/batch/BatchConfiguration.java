package com.abc.batch;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableAsync;

import com.abc.constant.Constants;
import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;


/**
 * Configuration for building batch jobs
 *
 */
@Configuration
@EnableBatchProcessing
@EnableAsync
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final Logger log = LoggerFactory.getLogger(Constants.APPLICATION_LOG_FILE_NAME);
    
    /**
     * reader bean, an instance of a FlatFileItemReader, that implements the ItemReader interface to read Policy Transactions records.
     * @param inputFilePath
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemReader<PolicyTransaction> reader(@Value("#{jobParameters[inputFilePath]}") String inputFilePath) {
    	log.info("Reading file :: " + inputFilePath);
    	
        FlatFileItemReader<PolicyTransaction> reader = new FlatFileItemReader<PolicyTransaction>();
        // Skips the header line of the input file
        reader.setLinesToSkip(1);
        
        //setting resource and line mapper
        reader.setResource(new FileSystemResource(inputFilePath));
        reader.setLineMapper(new DefaultLineMapper<PolicyTransaction>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "policyId", "policyHolderId" ,"dateOfService", "coverageMainCategory" ,"coverageSubCategory", "billedAmount" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<PolicyTransaction>() {{
                setTargetType(PolicyTransaction.class);
            }});
        }});
        
        return reader;
    }

    /**
     * the processor bean, an instance of the PolicyDataProcessor which implements ItemProcessor
     * Contains logic to process the input policy records
     * @return
     */
    @Bean
    public PolicyDataProcessor processor() {
    	log.info("Processing data");
        return new PolicyDataProcessor();
    }

    
    /**
     * the writer bean, an instance of a FlatFileItemWriter, that implements the ItemWriter interface to write the processed Policy Transaction to the CSV file.
     * @param outputFilePath
     * @return
     */
    @Bean
    @StepScope
    public FlatFileItemWriter<PolicyTransaction> writer(@Value("#{jobParameters[outputFilePath]}") String outputFilePath) {
    	
    	log.info("Writing file :: " + outputFilePath);
    	
    	FlatFileItemWriter<PolicyTransaction> writer = new FlatFileItemWriter<PolicyTransaction>();
    	writer.setResource(new FileSystemResource(new File(outputFilePath)));
    	writer.setShouldDeleteIfEmpty(true);
    	writer.setShouldDeleteIfExists(true);
    	
    	DelimitedLineAggregator<PolicyTransaction> delLineAgg = new DelimitedLineAggregator<PolicyTransaction>();
    	delLineAgg.setDelimiter(",");
    	
    	BeanWrapperFieldExtractor<PolicyTransaction> fieldExtractor = new BeanWrapperFieldExtractor<PolicyTransaction>();
    	fieldExtractor.setNames(new String[] { "policyId", "policyHolderId", "dateOfService", "coverageMainCategory",
				"coverageSubCategory", "billedAmount", "policyHolderAmt", "planAmt", "deductibleRule", "individualAccumulatedDed",
				"familyAccumulatedDed", "errorCode", "errorMessage", "processingMessage"});
    	delLineAgg.setFieldExtractor(fieldExtractor);
    	writer.setLineAggregator(delLineAgg);
    	
    	// Adds header to the file
    	writer.setHeaderCallback(new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Policy Id,Policy Holder Id,Date Of Service,Coverage Main Category,Coverage Sub Category,Billed Amount,Policy Holder Pays,Plan Pays,Rule used,Individual accumulated Deductible as of Service Date,Family accumulated Deductible as of Service Date,Error Code,Error Message,Processing Message");
			}
		});
    	
    	return writer;
    }
    
    
    
    /**
     * SkipPolicy bean specifies conditions to skip the input records
     * @return
     */
    @Bean
    public SkipPolicy fileReadSkipper() {
    	return new FileReadSkipper();
    }
    
    /**
     *  Job bean, built using the JobBuilderFactory. 
     *  On calling JobBuilderFactory's get method, Spring Batch will create a job builder and will initialize its job repository, 
     *  the JobBuilder is the convenience class for building jobs
     * @return
     * @throws InterruptedException
     */
    @Bean
    public Job createPolicyTransaction() throws InterruptedException {
        return jobBuilderFactory.get("createPolicyTransaction")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }
    
    /**
     * Step bean is built using StepBuilderFactory. 
     * It calls the reader, processor and writer.
     * On calling the get method from the StepBuilderFactory, Spring Batch will create a step builder and will initialize its job repository and transaction manager, 
     *  the StepBuilder is an entry point for building all kinds of steps.
     * @return
     */
    @Bean
    public Step step() throws IllegalArgumentException {
		return stepBuilderFactory.get("step")
                .<PolicyTransaction, PolicyTransaction> chunk(Constants.CHUNK_SIZE)
                .reader(reader(Constants.OVERRIDDEN_BY_EXPRESSION)).faultTolerant().skipPolicy(fileReadSkipper())
                .processor(processor())
                .writer(writer(Constants.OVERRIDDEN_BY_EXPRESSION))
                .build();
    }
    
}