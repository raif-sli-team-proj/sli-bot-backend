package ru.hse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hse.statistics.model.IncidentStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;

    private String userId;

    private String contents;

    private Long incidentId;

    private IncidentStatus newIncidentStatus;

    private LocalDateTime creationDate;
}
