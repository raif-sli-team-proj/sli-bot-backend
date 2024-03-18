package ru.hse.statistics.model;

import java.time.LocalDateTime;

public record SliMetric(
        Double value,
        LocalDateTime dateTime
) {
}
