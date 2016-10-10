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
import org.springframework.transaction.annotation.Transactional;

import com.abc.constant.Constants;
import com.abc.entity.PolicyTransaction;
import com.abc.processor.PolicyDataProcessor;

@Configuration
@EnableBatchProcessing
@Transactional
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private static final Logger log = LoggerFactory.getLogger(PolicyDataProcessor.class);
    
    @Bean
    @StepScope
    public FlatFileItemReader<PolicyTransaction> reader(@Value("#{jobParameters[inputFilePath]}") String inputFilePath) {
    	log.info("Reading file :: " + inputFilePath);
    	
        FlatFileItemReader<PolicyTransaction> reader = new FlatFileItemReader<PolicyTransaction>();
        reader.setLinesToSkip(1);
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

    @Bean
    public PolicyDataProcessor processor() {
    	log.info("Processing data");
        return new PolicyDataProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<PolicyTransaction> writer(@Value("#{jobParameters[outputFilePath]}") String outputFilePath) {
    	
    	//outputFilePath = outputFilePath.concat("/SampleOutputTransactionFile.csv").concat(new Date().toString()).concat(".csv");
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
    	writer.setHeaderCallback(new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("Policy Id,Policy Holder Id,Date Of Service,Coverage Main Category,Coverage Sub Category,Billed Amount,Policy Holder Pays,Plan Pays,Rule used,Individual accumulated Deductible as of Service Date,Family accumulated Deductible as of Service Date,Error Code,Error Message,Processing Message");
			}
		});
        return writer;
    }

    @Bean
    public Job createPolicyTransaction() {
        return jobBuilderFactory.get("createPolicyTransaction")
                .incrementer(new RunIdIncrementer())
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<PolicyTransaction, PolicyTransaction> chunk(1)
                .reader(reader(Constants.OVERRIDDEN_BY_EXPRESSION))
                .processor(processor())
                .writer(writer(Constants.OVERRIDDEN_BY_EXPRESSION))
                .build();
    }
}