package com.abc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abc.constant.Constants;
import com.abc.processor.PolicyDataProcessor;

/**
 * Exception Handler
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {
	
	private static final Logger log = LoggerFactory.getLogger(Constants.APPLICATION_LOG_FILE_NAME);

	  @ExceptionHandler(Exception.class)
	  public void handleError(Exception ex) {
		  log.error("Exception occured :: " + ex);
	  }
}
