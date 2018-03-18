package fr.mbutton.kafka;

import fr.mbutton.avro.Message;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AvroProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.0.231:9092,192.168.0.232:9092,192.168.0.233:9092,192.168.0.234:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "http://localhost:8081");


        try (KafkaProducer<String, Message> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 100; i++) {
                ProducerRecord<String, Message> record = new ProducerRecord<>("testopic", new Message("Message-" + i, 1, "extra"));
                producer.send(record);
            }
        }
    }    
}
