package ru.hse.core.dto;

import lombok.Data;
import ru.hse.core.enums.IncidentStatus;

@Data
public class CommentDTO {
    private String userId;

    private String contents;

    private Long incidentId;

    private IncidentStatus newIncidentStatus;
}