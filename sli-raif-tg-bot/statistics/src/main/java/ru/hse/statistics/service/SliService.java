package ru.hse.statistics.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
            case "1h" -> getSliForHour(statuses);
            case "1d" -> Collections.emptyList();
            case "1w" -> Collections.emptyList();
            default -> Collections.emptyList();
        };
    }

    private List<SliMetric> getSliForHour(List<ServiceStatus> statuses) {
       var result = statuses.stream()
                .collect(Collectors.groupingBy(status ->
                                status.getAddDate().withMinute(0).withSecond(0).withNano(0),
                        Collectors.collectingAndThen(Collectors.toList(), statusesByHour -> {
                            double failedCount =
                                    statusesByHour.stream().filter(s -> s.getStatus() == Status.FAIL).toList().size();
                            double totalCount = statusesByHour.size();
                            return 1 - failedCount / totalCount;
                        })));

        return result.entrySet().stream()
                .map(e -> new SliMetric(e.getValue(), e.getKey().toLocalDateTime()))
                .sorted(Comparator.comparing(SliMetric::dateTime))
                .toList();
    }
}
