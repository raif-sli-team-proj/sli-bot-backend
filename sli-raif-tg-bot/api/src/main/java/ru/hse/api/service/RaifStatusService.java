package ru.hse.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.api.client.RaifClient;
import ru.hse.api.dto.ServiceDTO;
import ru.hse.api.dto.Status;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RaifStatusService {
    private final RaifClient raifStatusClient;

    public List<ServiceDTO> getFpsServicesStatuses() {
        return raifStatusClient.getFPSStatusInfo().getServices().stream()
                .map(service -> new ServiceDTO(service.getName(), service.getStatuses(), 1.0,
                        new Status(LocalDate.now(), "OK"))).toList();
    }

}
