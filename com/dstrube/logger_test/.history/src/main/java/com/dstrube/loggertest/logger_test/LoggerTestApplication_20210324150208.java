package com.dstrube.loggertest.logger_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@SpringBootApplication
public class LoggerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerTestApplication.class, args);
		Logger loggerF = LogManager.getFormatterLogger();
		Logger logger = LogManager.getLogger(LoggerTestApplication.class);
		loggerF.info("arg0 and {}", 1);
		logger.info("arg1 and {}", 1);
	}

}
