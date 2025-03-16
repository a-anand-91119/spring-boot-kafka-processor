package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.clients;

import dev.notyouraverage.project.core.dtos.kafka.ProcessRequestPayload;
import dev.notyouraverage.project.core.dtos.kafka.ProcessedResponsePayload;
import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.transformers.GrpcKafkaPayloadTransformer;
import dev.notyouraverage.project.one.proto.GetNameStatsRequest;
import dev.notyouraverage.project.one.proto.GetNameStatsResponse;
import dev.notyouraverage.project.one.proto.NameServiceGrpc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NameServiceClient {

    private final NameServiceGrpc.NameServiceBlockingStub nameServiceBlockingStub;

    private final GrpcKafkaPayloadTransformer grpcKafkaPayloadTransformer;

    public ProcessedResponsePayload getNameStats(ProcessRequestPayload processRequestPayload) {
        log.info("Making GRPC call...");
        GetNameStatsResponse getNameStatsResponse = nameServiceBlockingStub.getNameStats(
                GetNameStatsRequest.newBuilder()
                        .setRequestId(processRequestPayload.getRequestId())
                        .setName(processRequestPayload.getName().toUpperCase())
                        .build()
        );
        log.info("Completed GRPC call...");
        return grpcKafkaPayloadTransformer.convertToProcessedResponsePayload(getNameStatsResponse);
    }

}
