package com.dstrube.loggertest.logger_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Made by (and runnable in) VSCode, so please excuse all the cruft.
 */

@SpringBootApplication
public class LoggerTestApplication {

	public static void main(String[] args) {
		//This shows up before the Spring banner in the log
		Logger loggerF = LogManager.getFormatterLogger();
		loggerF.info("arg0 and {}", 1);

		SpringApplication.run(LoggerTestApplication.class, args);

		Logger logger = LogManager.getLogger(LoggerTestApplication.class);
		logger.info("arg1 and {}", 1);
		logger.info("arg1 and {} {}", 1, 2);
	}

}
