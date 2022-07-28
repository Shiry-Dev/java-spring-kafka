package br.com.spring.kafka.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerSpringKafka {

    private final Logger logger = LoggerFactory.getLogger(ConsumerSpringKafka.class);

    @KafkaListener(topics = "${topic.spring-kafka}", groupId = "group_id")
    public void consume(String mensagem) throws IOException {
        logger.info(String.format(" #### -> Consumindo mensagem -> %s", mensagem));
    }
}