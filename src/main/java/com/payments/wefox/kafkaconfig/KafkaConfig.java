package com.payments.wefox.kafkaconfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.payments.wefox.entity.Payments;

/**
 * 
 * @author Sakharam
 *
 */
@EnableKafka
@Configuration
public class KafkaConfig { 

    @Bean
    public ConsumerFactory<String, Payments> consumerFactory() 
    { 
        // Creating a map of string-object type 
        Map<String, Object> config = new HashMap<>(); 
        // Adding the Configuration 
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092"); 
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id"); 
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); 
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class); 

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),new JsonDeserializer<>(Payments.class)); 

    } 

    // Creating a Listener 

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Payments> paymentListener() 
    { 
        ConcurrentKafkaListenerContainerFactory<String, Payments> factory = new ConcurrentKafkaListenerContainerFactory<>(); 
        factory.setConsumerFactory(consumerFactory()); 
        factory.setAutoStartup(false);
        return factory; 

    } 
}