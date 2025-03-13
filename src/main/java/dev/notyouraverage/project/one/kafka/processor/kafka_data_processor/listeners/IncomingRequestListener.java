package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.listeners;

import dev.notyouraverage.project.dtos.kafka.ProcessedResponsePayload;
import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.constants.Constants;
import dev.notyouraverage.project.dtos.kafka.ProcessRequestPayload;
import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.core.JsonSerializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncomingRequestListener {

    private final KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    private final String responseTopic;

    public IncomingRequestListener(
            @Qualifier(Constants.JSON_SERIALIZABLE_KAFKA_TEMPLATE) KafkaTemplate<String, JsonSerializable> kafkaTemplate,
            @Value("${project.kafka.responseTopic.name}") String responseTopic
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.responseTopic = responseTopic;
    }

    @KafkaListener(id = Constants.LISTENER_REQEUST_MESSAGES, topics = "${project.kafka.requestTopic.name}", containerFactory = Constants.JSON_SERIALIZABLE_CONCURRENT_LISTENER_CONTAINER_FACTORY)
    public void onMessage(
            @Payload ProcessRequestPayload requestPayload,
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info("Received(key={}, partition={}): {}", key, partition, requestPayload);
        kafkaTemplate.send(
                responseTopic,
                ProcessedResponsePayload.builder()
                        .requestId(requestPayload.getRequestId())
                        .name(requestPayload.getName().toUpperCase())
                        .count(1L)
                        .build()
        );
    }
}
