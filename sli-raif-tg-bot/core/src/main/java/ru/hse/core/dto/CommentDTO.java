package ru.hse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.statistics.model.IncidentStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String userId;

    private String contents;

    private Long incidentId;

    private IncidentStatus newIncidentStatus;
}
