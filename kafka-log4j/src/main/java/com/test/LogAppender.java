package com.test;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * @ClassName LogAppender
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/10/21 22:18
 * @Version 1.0
 **/
public class LogAppender extends DailyRollingFileAppender {
    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.getThreshold().equals(priority);
    }
}
