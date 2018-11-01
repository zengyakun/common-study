package com.kafka.action.spark.online.job;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.codehaus.jackson.map.deser.std.StringDeserializer;
import scala.Tuple2;

import java.util.*;

/**
 * @ClassName WordsTopSearchJob
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/1 17:03
 * @Version 1.0
 **/
public class WordsTopSearchJob {

    public static Map<String, Object> initKafkaConsumerConf() {
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "server-1:9092,server-2:9092,server-3:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "words-top-search");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);
        return kafkaParams;
    }

    public static void println(List<Tuple2<String, Long>> wordConutList) {
        if (CollectionUtils.isNotEmpty(wordConutList)) {
            List<Tuple2<String, Long>> sortList = new ArrayList<>(wordConutList);
            sortList.sort(new Comparator<Tuple2<String, Long>>() {
                @Override
                public int compare(Tuple2<String, Long> t1, Tuple2<String, Long> t2) {
                    if (t2._2.compareTo(t1._2) > 0) {
                        return 1;
                    } else if (t2._2.compareTo(t1._2) < 0) {
                        return -1;
                    }
                    return 0;
                }
            });
            System.out.println("=======================================");
            System.out.println("时间：[" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "]，热搜词如下：");
            for (Tuple2<String, Long> wordCount : sortList) {
                System.out.println(wordCount._1 + ":" + wordCount._2);
            }

            System.out.println("=======================================");
        }
    }

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("kafka-sparkstreaming").setMaster("spark://server-1:7077");
        JavaStreamingContext streamContext = new JavaStreamingContext(sparkConf, new Duration(10000));
        Map<String, Object> kafkaParams = initKafkaConsumerConf();
        streamContext.checkpoint("/words-top-search");
        try {
            final JavaInputDStream<ConsumerRecord<String, String>> inputDStream = null;

        } catch (Exception e) {
        }

    }
}
