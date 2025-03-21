package dev.notyouraverage.project.core.dtos.kafka;

import dev.notyouraverage.project.core.JsonSerializable;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessRequestPayload implements JsonSerializable {
    private String requestId;

    private String name;
}
