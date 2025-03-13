package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.utils;

import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Map;

@UtilityClass
public class KafkaConfigurationUtils {

    public static Map<String, Object> buildCommonProducerConfigs(KafkaProperties kafkaProperties) {
        return kafkaProperties.buildProducerProperties(null);
    }

    public static Map<String, Object> buildCommonConsumerConfigs(KafkaProperties kafkaProperties) {
        return kafkaProperties.buildConsumerProperties(null);
    }
}
