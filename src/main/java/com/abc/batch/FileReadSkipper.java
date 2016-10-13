package com.abc.batch;


import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

import com.abc.constant.Constants;


public class FileReadSkipper implements SkipPolicy {
	private static final Logger logger = LoggerFactory.getLogger(Constants.SKIP_READ_LOG_FILENAME);

	// Throws FileNotFoundException if input file is not found.
	// Throws FlatFileParseException if more than 5 input records fails parsing
	// Skips the FlatFileParseException for input records less than or equal to 5.
	@Override
	public boolean shouldSkip(Throwable exception, int skipCount) throws SkipLimitExceededException {
		if (exception instanceof FileNotFoundException) {
			return false;
		} else if (exception instanceof FlatFileParseException && skipCount <= 5) {
			
			FlatFileParseException ffpe = (FlatFileParseException) exception;
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("An error occured while processing the " + ffpe.getLineNumber()
					+ " line of the file. Below was the faulty " + "input.\n");
			errorMessage.append(ffpe.getInput() + "\n");
			logger.error("{}", errorMessage.toString());
			return true;
		} else {
			return false;
		}
	}
}
