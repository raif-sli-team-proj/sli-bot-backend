package ru.hse.statistics.service;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.statistics.model.SliMetric;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.repository.ServiceStatusRepository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Service
@RequiredArgsConstructor
public class SliService {

    private final ServiceStatusRepository serviceStatusRepository;

    public List<SliMetric> getServiceSli(String serviceName, String frame) {
        OffsetDateTime thirtyDaysAgo = OffsetDateTime.now(ZoneOffset.UTC).minusDays(30);

        List<ServiceStatus> statuses = serviceStatusRepository.findAllStatusesAfter(serviceName,
                OffsetDateTime.from(thirtyDaysAgo));

        return switch (frame) {
            case "1h" -> getSliForHours(statuses);
            case "1d" -> getSliForDays(statuses);
            case "1w" -> getSliForWeeks(statuses);
            default -> Collections.emptyList();
        };
    }

    private List<SliMetric> getSliForHours(List<ServiceStatus> statuses) {
       var result = statuses.stream()
                .collect(Collectors.groupingBy(status ->
                                status.getAddDate().withMinute(0).withSecond(0).withNano(0),
                        Collectors.collectingAndThen(Collectors.toList(), this::countSli)));

        return getSliMetrics(result);
    }

    private List<SliMetric> getSliForDays(List<ServiceStatus> statuses) {
        var result = statuses.stream()
                .collect(Collectors.groupingBy(status ->
                                status.getAddDate().withHour(0).withMinute(0).withSecond(0).withNano(0),
                        Collectors.collectingAndThen(Collectors.toList(), this::countSli)));

        return getSliMetrics(result);
    }

    private List<SliMetric> getSliForWeeks(List<ServiceStatus> statuses) {
        var result = statuses.stream()
                .collect(Collectors.groupingBy(status ->
                                status.getAddDate()
                                        .withHour(0).withMinute(0).withSecond(0).withNano(0)
                                        .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)),
                        Collectors.collectingAndThen(Collectors.toList(), this::countSli)));

        return getSliMetrics(result);
    }

    private Double countSli(List<ServiceStatus> statuses) {
        double failedCount =
                statuses.stream().filter(s -> s.getStatus() == Status.FAIL).toList().size();
        double totalCount = statuses.size();
        return 1 - failedCount / totalCount;
    }

    private List<SliMetric> getSliMetrics(Map<OffsetDateTime, Double> dateTimeToSli) {
        return dateTimeToSli.entrySet().stream()
                .map(e -> new SliMetric(e.getValue(), e.getKey().toLocalDateTime()))
                .sorted(Comparator.comparing(SliMetric::dateTime))
                .toList();
    }
}
