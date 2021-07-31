package com.hzvh.kafka;

import com.hzvh.dao.HbaseDAO;
import com.hzvh.utils.PropertyUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

public class HbaseConsumer {
    public static void main(String[] args) throws IOException, ParseException {
        //获取kafka消费者
        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<>(PropertyUtil.properties);
        kafkaConsumer.subscribe(Collections.singletonList(PropertyUtil.getProperty("kafka.topic")));


        HbaseDAO hbaseDAO = new HbaseDAO();
        while(true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(300);
            for (ConsumerRecord<String, String> record: records){
                String ori = record.value();
                System.out.println(ori);
                hbaseDAO.put(ori);
            }
        }

    }
}
