package ru.hse.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import ru.hse.statistics.model.SliMetric;

public record GetSliResponse(
        List<SliMetric> metrics,
        LocalDateTime leftDateTime,
        LocalDateTime rightDateTime,
        String frameSize
) {
}