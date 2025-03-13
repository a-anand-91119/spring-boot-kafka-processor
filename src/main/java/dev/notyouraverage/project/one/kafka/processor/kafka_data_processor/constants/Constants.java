package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static final String JSON_SERIALIZABLE_PRODUCER_FACTORY = "jsonSerializerPublicClusterProducerFactory";

    public static final String JSON_SERIALIZABLE_KAFKA_TEMPLATE = "jsonSerializerPublicKafkaTemplate";

    public static final String LISTENER_REQEUST_MESSAGES = "listenerRequestMessages";

    public static final String JSON_SERIALIZABLE_CONCURRENT_LISTENER_CONTAINER_FACTORY = "jsonSerializerConcurrentListenerContainerFactory";

    public static final String JSON_SERIALIZABLE_CONSUMER_FACTORY = "jsonSerializerConsumerFactory";
}
