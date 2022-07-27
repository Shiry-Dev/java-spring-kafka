package br.com.spring.kafka.producer.service;

import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {
    private static final Logger logger = LoggerFactory.getLogger(MensagemService.class);

    private static String topicSpringKafka;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //envio de mensagem
    public void sendMessage(String message){
        logger.info("Mensagem -> {}", message);
        this.kafkaTemplate.send(topicSpringKafka, message);
    }
}
