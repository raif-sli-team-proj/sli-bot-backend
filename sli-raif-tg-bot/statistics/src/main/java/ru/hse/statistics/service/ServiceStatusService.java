package ru.hse.statistics.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.statistics.repository.ServiceStatusRepository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Service
@AllArgsConstructor
public class ServiceStatusService {
    private final ServiceStatusRepository serviceStatusRepository;

    public void addCurrentStatuses() {
        var e = new ServiceStatus();
        e.setServiceName("test");
        e.setStatus(ServiceStatus.Status.UNKNOWN);
        e.setAddDate(OffsetDateTime.now(ZoneOffset.UTC));

        serviceStatusRepository.save(e);
    }
}
