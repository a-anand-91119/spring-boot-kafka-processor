package dev.notyouraverage.project.dtos.kafka;

import dev.notyouraverage.project.one.kafka.processor.kafka_data_processor.core.JsonSerializable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequestPayload implements JsonSerializable {
    private String requestId;

    private String name;
}
