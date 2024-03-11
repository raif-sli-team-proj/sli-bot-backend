package ru.hse.core.dto;

import lombok.Data;
import ru.hse.statistics.model.IncidentStatus;

@Data
public class CommentDTO {
    private String userId;

    private String contents;

    private Long incidentId;

    private IncidentStatus newIncidentStatus;
}
