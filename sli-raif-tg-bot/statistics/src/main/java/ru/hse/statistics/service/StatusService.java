package ru.hse.statistics.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.statistics.client.OnlineServiceStatusClient;
import ru.hse.statistics.enumeration.RaifProduct;
import ru.hse.statistics.enumeration.RaifService;
import ru.hse.statistics.model.ProductOnlineStatusesResponse;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.repository.ServiceStatusRepository;
import ru.hse.statistics.repository.entity.ServiceStatus;

@Service
@Slf4j
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

    @Transactional
    public void addStatus(RaifService service, Status status) {
        serviceStatusRepository.save(
                ServiceStatus.builder()
                        .productName(service.product)
                        .serviceName(service.service)
                        .status(status)
                        .addDate(OffsetDateTime.now(ZoneOffset.UTC))
                        .build());
    }

    @Transactional
    public void addStatuses(Map<RaifService, Status> serviceToStatus) {
        OffsetDateTime dateTime = OffsetDateTime.now(ZoneOffset.UTC);
        List<ServiceStatus> statuses = serviceToStatus.entrySet().stream()
                .map(e -> ServiceStatus.builder()
                        .productName(e.getKey().product)
                        .serviceName(e.getKey().service)
                        .status(e.getValue())
                        .addDate(dateTime)
                        .build()
                ).toList();
        serviceStatusRepository.saveAll(statuses);
    }

    /**
     * Получить статусы сервисов по продукту.
     */
    public Map<RaifService, Status> getProductServiceStatuses(RaifProduct product) {
        ProductOnlineStatusesResponse response;
        try {
            response = serviceStatusClient.getOnlineStatusesByProduct(product);
        } catch (Exception e) {
            log.error(String.format("Error getting statuses for product %s", product.productName), e);
            return Map.of();
        }
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

    /**
     * Получить онлайн статусы всех сервисов.
     * */
    public Map<RaifService, Status> getAllServiceStatuses() {
        List<RaifProduct> products = List.of(RaifProduct.values());
        Map<RaifService, Status> serviceToStatus = new HashMap<>();
        products.forEach(p -> serviceToStatus.putAll(getProductServiceStatuses(p)));
        return serviceToStatus;
    }
}
