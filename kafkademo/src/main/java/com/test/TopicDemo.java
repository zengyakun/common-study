package com.test;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsOptions;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName TopicTest
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/10/18 15:02
 * @Version 1.0
 **/
public class TopicDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.101.0.223:9092");
        AdminClient adminClient = AdminClient.create(props);
        List<NewTopic> topics = new ArrayList<>();
        NewTopic newTopic = new NewTopic("topic-test2", 1, (short) 1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
