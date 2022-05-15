package com.payments.wefox.kafkaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class KafkaControllerService {

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public void stop() {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry
                .getListenerContainer("paymentTopic");
        listenerContainer.stop();
    }

    public void start() {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry
                .getListenerContainer("paymentTopic");
        listenerContainer.start();
    }


}
