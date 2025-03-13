package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "dev.notyouraverage.project.base", "dev.notyouraverage.project.one" })
public class KafkaDataProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDataProcessorApplication.class, args);
    }

}
