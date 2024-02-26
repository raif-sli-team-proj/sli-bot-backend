package ru.hse.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;

    private String userId;

    private String contents;

    private Long incidentId;
}
