package dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.transformers;

import dev.notyouraverage.project.base.annotations.Transformer;
import dev.notyouraverage.project.core.dtos.kafka.ProcessedResponsePayload;
import dev.notyouraverage.project.one.proto.GetNameStatsResponse;

@Transformer
public class GrpcKafkaPayloadTransformer {

    public ProcessedResponsePayload convertToProcessedResponsePayload(GetNameStatsResponse getNameStatsResponse) {
        return ProcessedResponsePayload.builder()
                .requestId(getNameStatsResponse.getRequestId())
                .count(getNameStatsResponse.getCount())
                .name(getNameStatsResponse.getName())
                .build();
    }
}
