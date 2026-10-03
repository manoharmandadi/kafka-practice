package com.pm.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaPublisher {

    private final KafkaProducer<String, String> producer;
    private final String topic;

    public KafkaPublisher(String bootstrapServers, String topic) {
        this.topic = topic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(props);
    }

    public void publish(String key, String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("Message sent successfully to topic " + metadata.topic() +
                        " partition " + metadata.partition() + " offset " + metadata.offset());
            } else {
                System.err.println("Error sending message: " + exception.getMessage());
            }
        });
    }

    public void close() {
        producer.close();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: KafkaPublisher <bootstrap-servers> <topic>");
            return;
        }
        String bootstrapServers = args[0];
        String topic = args[1];

        KafkaPublisher publisher = new KafkaPublisher(bootstrapServers, topic);
        // Example: publish a message
        publisher.publish("key1", "Hello, Kafka Client!");
        // Close after some time or in a real app, handle properly
        publisher.close();
    }
}
