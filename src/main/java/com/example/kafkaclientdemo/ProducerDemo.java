package com.example.kafkaclientdemo;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        // 키를 전송하지 않아도 key.serializer 설정 값은 필수
        properties.put("key.serializer", StringSerializer.class);
        properties.put("value.serializer", StringSerializer.class);
        Producer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>("CustomerCountry", "Korea");
        Future<RecordMetadata> send = producer.send(record);
        RecordMetadata recordMetadata = null;
        try {
            recordMetadata = send.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("", e);
        }
        if (recordMetadata != null) {
            log.error("{}", recordMetadata);
        }
    }
}
