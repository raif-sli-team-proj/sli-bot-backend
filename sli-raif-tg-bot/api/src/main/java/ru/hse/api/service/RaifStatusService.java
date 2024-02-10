package ru.hse.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.hse.api.dto.FPSStatusInfo;
import ru.hse.api.dto.ServiceDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaifStatusService {

    private final WebClient webClient;

    public Mono<List<ServiceDTO>> getFPSServiceStatuses() {
        return webClient
                .get()
                .uri("/FPS")
                .retrieve()
                .bodyToMono(FPSStatusInfo.class)
                .map(value ->
                    value.getServices().stream()
                            .map(service -> new ServiceDTO(service.getName(), service.getStatuses(), 1.0))
                            .toList()
                );
    }

}
