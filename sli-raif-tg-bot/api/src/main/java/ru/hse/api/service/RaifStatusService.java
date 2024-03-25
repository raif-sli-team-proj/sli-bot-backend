package ru.hse.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.api.client.RaifClient;
import ru.hse.api.dto.ServiceDto;
import ru.hse.api.dto.StatusDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ru.hse.api.dto.raif.RaifServiceDto;
import ru.hse.statistics.enumeration.RaifService;
import ru.hse.statistics.model.SliMetric;
import ru.hse.statistics.model.Status;
import ru.hse.statistics.service.SliService;
import ru.hse.statistics.service.StatusService;

@Service
@RequiredArgsConstructor
public class RaifStatusService {
    private final RaifClient raifStatusClient;
    private final StatusService statusService;
    private final SliService sliService;

    public List<ServiceDto> getAllServiceStatuses() {
        Map<RaifService, RaifServiceDto> serviceToDto = new HashMap<>();
        raifStatusClient.getAllProductsInfo().forEach(p -> {
                    Map<RaifService, RaifServiceDto> services = p.services().stream()
                            .collect(Collectors.toMap(
                                    s -> RaifService.of(p.externalId(), s.externalId()),
                                    s -> s
                            ));
                    serviceToDto.putAll(services);
                }
        );

        LocalDate nowTime = LocalDate.now();
        Map<RaifService, Status> serviceToOnlineStatus = statusService.getAllServiceStatuses();
        Map<RaifService, SliMetric> serviceToSli = sliService.countSliForServices();
        return serviceToDto.entrySet().stream()
                .map(s -> new ServiceDto(
                        s.getValue().externalId(),
                        s.getValue().name(),
                        s.getValue().statuses(),
                        serviceToSli.get(s.getKey()).value(),
                        new StatusDto(nowTime, mapStatus(serviceToOnlineStatus.get(s.getKey())))
                )).toList();
    }

    private String mapStatus(Status status) {
        if (status == Status.OK) {
            return "ON";
        }
        return "OFF";
    }

}
