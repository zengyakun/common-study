package com.test.springkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName SimpleConsumerListener
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/10/18 16:25
 * @Version 1.0
 **/
public class SimpleConsumerListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleConsumerListener.class);
    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "topic-test")
    public void listen(String[] records) {
//        System.out.println(String.valueOf(records));
        System.out.println(records[0]);
        this.latch1.countDown();
    }

}
