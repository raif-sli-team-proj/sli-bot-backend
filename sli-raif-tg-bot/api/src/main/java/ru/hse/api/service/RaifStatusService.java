package ru.hse.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.api.client.RaifClient;
import ru.hse.api.dto.ServiceDTO;
import ru.hse.api.dto.Status;

import java.time.LocalDate;
import java.util.List;

import ru.hse.statistics.service.StatusService;

@Service
@RequiredArgsConstructor
public class RaifStatusService {
    private final RaifClient raifStatusClient;
    private final StatusService statusService;

    public List<ServiceDTO> getFpsServicesStatuses() {
        return raifStatusClient.getFPSStatusInfo().getServices().stream()
                .map(service -> new ServiceDTO(
                        service.getExternalId(),
                        service.getName(),
                        service.getStatuses(),
                        1.0,
                        new Status(LocalDate.now(), countStatus(service.getExternalId()))
                )).toList();
    }

    private String countStatus(String serviceId) {
        // TODO wait for fix inside statusService.getServiceStatus.
        var currentStatus = statusService.getServiceStatus(serviceId);
        if (currentStatus == ru.hse.statistics.model.Status.OK) {
            return "ON";
        }
        return "OFF";
    }

}
