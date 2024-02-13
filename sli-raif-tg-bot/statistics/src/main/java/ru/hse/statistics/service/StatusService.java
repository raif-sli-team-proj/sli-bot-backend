package ru.hse.statistics.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.repository.ServiceStatusRepository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final ServiceStatusRepository serviceStatusRepository;

    public Status getServiceStatus(String serviceName) {
        int randomNum = (int) (Math.random() * 10);
        return switch (randomNum) {
            case 8, 9 -> Status.FAIL;
            default -> Status.OK;
        };
    }

    public void addStatus(Status status) {
        var e = new ServiceStatus();
        e.setServiceName("FPS");
        e.setEndpointName("QRC");
        e.setStatus(status);
        e.setAddDate(OffsetDateTime.now(ZoneOffset.UTC));

        serviceStatusRepository.save(e);
    }
}
