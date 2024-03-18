package com.gdu.prj05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainClass {

  private static final Logger logger = LoggerFactory.getLogger(MainClass.class);
  
  public static void main(String[] args) {

    logger.trace("trace");
    logger.debug("debug");
    logger.info("info");
    logger.warn("warn");
    logger.error("error");

  }

}
