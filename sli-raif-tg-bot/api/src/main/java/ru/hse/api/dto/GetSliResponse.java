package ru.hse.api.dto;

import java.util.List;

import ru.hse.statistics.model.SliMetric;

public record GetSliResponse(
        List<SliMetric> metrics,
        String service,
        String frameSize
) {
}