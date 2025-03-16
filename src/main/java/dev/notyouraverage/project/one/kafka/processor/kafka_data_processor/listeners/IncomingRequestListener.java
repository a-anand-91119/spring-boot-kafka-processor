package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.listeners;

import dev.notyouraverage.project.core.JsonSerializable;
import dev.notyouraverage.project.core.dtos.kafka.ProcessedResponsePayload;
import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.constants.Constants;
import dev.notyouraverage.project.core.dtos.kafka.ProcessRequestPayload;
import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.clients.NameServiceClient;
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

    private final NameServiceClient nameServiceClient;

    public IncomingRequestListener(
            @Qualifier(Constants.JSON_SERIALIZABLE_KAFKA_TEMPLATE) KafkaTemplate<String, JsonSerializable> kafkaTemplate,
            @Value("${project.kafka.responseTopic.name}") String responseTopic,
            NameServiceClient nameServiceClient
    ) {
        this.kafkaTemplate = kafkaTemplate;
        this.responseTopic = responseTopic;
        this.nameServiceClient = nameServiceClient;
    }

    @KafkaListener(id = Constants.LISTENER_REQEUST_MESSAGES, topics = "${project.kafka.requestTopic.name}", containerFactory = Constants.JSON_SERIALIZABLE_CONCURRENT_LISTENER_CONTAINER_FACTORY)
    public void onMessage(
            @Payload ProcessRequestPayload requestPayload,
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info("Received Request (key={}, partition={}): {}", key, partition, requestPayload);
        ProcessedResponsePayload nameStats = nameServiceClient.getNameStats(requestPayload);
        kafkaTemplate.send(responseTopic, key, nameStats);
        log.info("Sent Response (key={}): {}", key, nameStats);
    }
}
