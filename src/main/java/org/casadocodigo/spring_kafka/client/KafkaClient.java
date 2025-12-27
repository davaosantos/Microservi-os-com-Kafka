package org.casadocodigo.spring_kafka.client;

import lombok.RequiredArgsConstructor;
import org.casadocodigo.spring_kafka.dto.ShopDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClient {
    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";
    public void sendMessage(ShopDTO msg) {
        kafkaTemplate.send(SHOP_TOPIC_NAME, msg);
    }
}
