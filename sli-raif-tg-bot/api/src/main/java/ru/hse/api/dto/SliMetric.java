package ru.hse.api.dto;

import java.time.LocalDateTime;

public record SliMetric(
        Double value,
        LocalDateTime dateTime
) {
}
