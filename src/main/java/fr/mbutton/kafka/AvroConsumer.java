package fr.mbutton.kafka;

import fr.mbutton.avro.Message;
import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class AvroConsumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.231:9092,192.168.0.232:9092,192.168.0.233:9092,192.168.0.234:9092");
        props.put("group.id", "mygroup");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        props.put("schema.registry.url", "http://localhost:8081");

        try (KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("testopic"));
            
            while (true) {
                ConsumerRecords<String, Message> records = consumer.poll(100);
                for (ConsumerRecord<String, Message> record : records) {
                    System.out.println(record.value());
                }
            }
        }
    }
}
