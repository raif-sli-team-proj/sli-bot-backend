package ru.hse.core.dto;

import lombok.Data;

@Data
public class CommentaryDTO {
    private String userId;

    private String contents;

    private Long incidentId;
}
