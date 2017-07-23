package com.ahhf.ljxbw.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogBackTest {

	static Logger logger = LoggerFactory.getLogger(LogBackTest.class);

	public static void main(String[] args) {
		System.out.println("start");
		logger.info("logger.infoMessage.");
		logger.info("logger.infoMessage111.");
		logger.info("logger.infoMessage222.");
	}

}
