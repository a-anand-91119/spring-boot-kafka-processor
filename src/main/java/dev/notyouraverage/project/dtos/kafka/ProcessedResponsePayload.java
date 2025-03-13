package dev.notyouraverage.project.dtos.kafka;

import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.core.JsonSerializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedResponsePayload implements JsonSerializable {
    private String requestId;

    private String name;

    private Long count;
}
