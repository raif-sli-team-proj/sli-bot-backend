package ru.hse.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record GetSliResponse(
        List<SliMetric> metrics,
        LocalDateTime leftDateTime,
        LocalDateTime rightDateTime,
        String frameSize
) {
}