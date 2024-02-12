package ru.hse.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.api.dto.GetSliResponse;
import ru.hse.api.dto.SliMetric;
import ru.hse.api.dto.SliServicesResponse;

@RestController
@RequestMapping("api/v1/sli")
public class SliController {

    @GetMapping("/services")
    public SliServicesResponse getServices() {
        return new SliServicesResponse(Set.of());
    }

    @GetMapping("/{service}")
    public GetSliResponse getSli(
            @PathVariable String service,
            @RequestParam String frame,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime leftDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime rightDateTime
    ) {
        return new GetSliResponse(List.of(
                new SliMetric(0.75, LocalDateTime.of(2024, 2, 1, 0, 0)),
                new SliMetric(0.79, LocalDateTime.of(2024, 2, 2, 0, 0)),
                new SliMetric(0.8, LocalDateTime.of(2024, 2, 3, 0, 0)),
                new SliMetric(0.84, LocalDateTime.of(2024, 2, 4, 0, 0)),
                new SliMetric(0.81, LocalDateTime.of(2024, 2, 5, 0, 0)),
                new SliMetric(0.87, LocalDateTime.of(2024, 2, 6, 0, 0)),
                new SliMetric(0.9, LocalDateTime.of(2024, 2, 7, 0, 0)),
                new SliMetric(0.94, LocalDateTime.of(2024, 2, 8, 0, 0)),
                new SliMetric(0.95, LocalDateTime.of(2024, 2, 9, 0, 0)),
                new SliMetric(1.0, LocalDateTime.of(2024, 2, 10, 0, 0)),
                new SliMetric(1.0, LocalDateTime.of(2024, 2, 11, 0, 0)),
                new SliMetric(0.9, LocalDateTime.of(2024, 2, 12, 0, 0))
        ),
                leftDateTime,
                rightDateTime,
                frame);
    }
}
