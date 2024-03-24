package ru.hse.statistics.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.statistics.client.OnlineServiceStatusClient;
import ru.hse.statistics.enumeration.RaifProduct;
import ru.hse.statistics.enumeration.RaifService;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.repository.ServiceStatusRepository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final ServiceStatusRepository serviceStatusRepository;
    private final OnlineServiceStatusClient serviceStatusClient;

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

    public Map<RaifService, Status> getProductServiceStatuses(RaifProduct product) {
        var response = serviceStatusClient.getOnlineStatusesByProduct(product);
        var services = response.services();
        return services.stream().collect(Collectors.toMap(
                s -> RaifService.of(product.productName, s.externalId()),
                s -> switch (s.online()) {
                    case "ON" -> Status.OK;
                    case "OFF" -> Status.FAIL;
                    default -> Status.UNKNOWN;
                }
        ));
    }
}
