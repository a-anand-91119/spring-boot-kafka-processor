package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.listeners;

import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.constants.Constants;
import dev.notyouraverage.project.dtos.kafka.ProcessRequestPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncomingRequestListener {

    @KafkaListener(id = Constants.LISTENER_REQEUST_MESSAGES, topics = "${project.kafka.requestTopic.name}", containerFactory = Constants.JSON_SERIALIZABLE_CONCURRENT_LISTENER_CONTAINER_FACTORY)
    public void onMessage(@Payload ProcessRequestPayload requestPayload,
                          @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        log.info("Received(key={}, partition={}): {}", key, partition, requestPayload);
    }
}
