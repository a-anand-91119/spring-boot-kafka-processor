package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.configurations;

import dev.notyouraverage.project.one.proto.NameServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.EnableGrpcClients;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
@EnableGrpcClients
public class GrpcConfigurations {

    @Bean
    NameServiceGrpc.NameServiceBlockingStub stub(GrpcChannelFactory channels) {
        return NameServiceGrpc.newBlockingStub(channels.createChannel("name-service"));
    }

}
