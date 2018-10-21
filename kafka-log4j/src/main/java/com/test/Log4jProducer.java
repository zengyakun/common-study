package com.test;

import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class Log4jProducer {

    private static final Logger LOG = Logger.getLogger(Log4jProducer.class);

    public static void main(String[] args) {
        LOG.info("这是一条info级别的日志！！");
        LOG.error("这是一条error级别的日志！！");
    }
}
